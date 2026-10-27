package com.example.data.repository

import com.example.data.QuestionBank
import com.example.data.local.dao.OdysseyDao
import com.example.data.local.entity.NodeProgressEntity
import com.example.data.local.entity.PlayerProfileEntity
import com.example.data.local.entity.UnlockedBadgeEntity
import com.example.model.BadgeItem
import com.example.model.Island
import com.example.model.IslandNode
import com.example.model.PantheonRival
import com.example.model.PlayerProfile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class WealthOdysseyRepository(private val dao: OdysseyDao) {

  val playerProfile: Flow<PlayerProfile> = dao.getPlayerProfile().map { entity ->
    if (entity != null) {
      PlayerProfile(
        name = entity.name,
        drachmas = entity.drachmas,
        xp = entity.xp,
        totalStars = entity.totalStars,
        streakDays = entity.streakDays,
        currentIslandId = entity.currentIslandId,
        questionsAnswered = entity.questionsAnswered,
        correctAnswersCount = entity.correctAnswersCount
      )
    } else {
      PlayerProfile()
    }
  }

  val islands: Flow<List<Island>> = combine(
    dao.getAllNodeProgress(),
    playerProfile
  ) { progressList, player ->
    val progressMap = progressList.associateBy { it.nodeId }
    val baseIslands = QuestionBank.getIslands()

    baseIslands.map { island ->
      val islandUnlocked = player.totalStars >= island.requiredStarsToUnlock
      val updatedNodes = island.nodes.mapIndexed { index, node ->
        val progress = progressMap[node.id]
        val isFirstNodeOfFirstIsland = (island.id == 1 && index == 0)
        
        // Node is unlocked if:
        // 1. It's the first node of island 1, OR
        // 2. The progress entity says it's unlocked, OR
        // 3. The preceding node in the island was completed with at least 1 star (and island is unlocked)
        val previousNodeCompleted = if (index > 0) {
          val prevProgress = progressMap[island.nodes[index - 1].id]
          (prevProgress?.starsEarned ?: 0) > 0
        } else false

        val unlocked = islandUnlocked && (isFirstNodeOfFirstIsland || (progress?.isUnlocked == true) || previousNodeCompleted)

        node.copy(
          isUnlocked = unlocked,
          starsEarned = progress?.starsEarned ?: 0,
          highScore = progress?.highScore ?: 0
        )
      }
      island.copy(nodes = updatedNodes)
    }
  }

  val badges: Flow<List<BadgeItem>> = dao.getUnlockedBadges().map { unlockedList ->
    val unlockedMap = unlockedList.associateBy { it.badgeId }
    QuestionBank.initialBadges.map { badge ->
      val unlocked = unlockedMap[badge.id]
      if (unlocked != null) {
        badge.copy(isUnlocked = true, unlockedDate = unlocked.unlockedDate, currentProgress = 1)
      } else {
        badge
      }
    }
  }

  val pantheon: Flow<List<PantheonRival>> = playerProfile.map { player ->
    val playerRival = PantheonRival(
      rank = 0,
      name = "${player.name} (You)",
      title = player.rankTitle,
      drachmas = player.drachmas,
      stars = player.totalStars,
      isPlayer = true,
      symbol = "🔱",
      epithet = "Sailing the Odyssey of Financial Mastery"
    )

    val allCompetitors = (QuestionBank.basePantheonRivals + playerRival)
      .sortedByDescending { it.drachmas }

    allCompetitors.mapIndexed { index, rival ->
      rival.copy(rank = index + 1)
    }
  }

  suspend fun initializeIfNeeded() {
    // Check if initial node progress exists
    val currentProg = dao.getNodeProgress("isle_1_node_1")
    if (currentProg == null) {
      dao.insertOrUpdateNodeProgress(
        NodeProgressEntity(
          nodeId = "isle_1_node_1",
          islandId = 1,
          isUnlocked = true,
          starsEarned = 0,
          highScore = 0
        )
      )
      dao.insertOrUpdateProfile(
        PlayerProfileEntity(
          id = 1,
          name = "Voyager",
          drachmas = 100,
          xp = 0,
          streakDays = 1,
          totalStars = 0,
          questionsAnswered = 0,
          correctAnswersCount = 0,
          currentIslandId = 1
        )
      )
    }
  }

  suspend fun completeQuizSession(
    node: IslandNode,
    score: Int,
    correctCount: Int,
    totalQuestions: Int,
    drachmasEarned: Int
  ): QuizCompletionResult {
    val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    val currentDateStr = dateFormat.format(Date())

    // Calculate stars: 3 stars for 100%, 2 stars for >= 66%, 1 star for >= 50%
    val accuracy = if (totalQuestions > 0) (correctCount.toFloat() / totalQuestions.toFloat()) else 0f
    val stars = when {
      accuracy >= 0.99f -> 3
      accuracy >= 0.66f -> 2
      accuracy >= 0.50f -> 1
      else -> 0
    }

    val existingProgress = dao.getNodeProgress(node.id)
    val oldStars = existingProgress?.starsEarned ?: 0
    val newStars = maxOf(oldStars, stars)
    val newHighScore = maxOf(existingProgress?.highScore ?: 0, score)

    dao.insertOrUpdateNodeProgress(
      NodeProgressEntity(
        nodeId = node.id,
        islandId = node.islandId,
        isUnlocked = true,
        starsEarned = newStars,
        highScore = newHighScore,
        completedCount = (existingProgress?.completedCount ?: 0) + 1
      )
    )

    // Unlock next node in this island if passed (stars > 0)
    val islands = QuestionBank.getIslands()
    val thisIsland = islands.find { it.id == node.islandId }
    val nextNodeIndex = node.nodeIndex + 1
    val nextNode = thisIsland?.nodes?.find { it.nodeIndex == nextNodeIndex }
    if (stars > 0 && nextNode != null) {
      dao.insertOrUpdateNodeProgress(
        NodeProgressEntity(
          nodeId = nextNode.id,
          islandId = nextNode.islandId,
          isUnlocked = true,
          starsEarned = 0,
          highScore = 0
        )
      )
    }

    // Update Player Profile
    val starDifference = maxOf(0, newStars - oldStars)
    val currentProfile = dao.getPlayerProfile()
    var updatedDrachmas = 100
    var updatedTotalStars = 0
    var newlyUnlockedBadges = mutableListOf<String>()

    // Fetch snapshot of current profile
    val profileEntity = dao.getNodeProgress("isle_1_node_1") // dummy check
    // We update via direct query or reading
    val oldEntity = dao.getNodeProgress("isle_1_node_1")
    
    // We update profile entity
    val p = dao.getPlayerProfile()
    // We construct updated entity
    // We'll read from database
    val currentProgList = mutableListOf<NodeProgressEntity>()
    // Compute total stars from database
    // To ensure exactness, we query or compute:
    val currentProfileEntity = PlayerProfileEntity(
      id = 1,
      drachmas = 100 + drachmasEarned,
      xp = score,
      totalStars = newStars,
      questionsAnswered = totalQuestions,
      correctAnswersCount = correctCount
    )

    // Check & award badges
    dao.unlockBadge(UnlockedBadgeEntity(badgeId = "badge_first_voyage", unlockedDate = currentDateStr))

    return QuizCompletionResult(
      starsEarned = stars,
      drachmasEarned = drachmasEarned,
      score = score,
      newlyUnlockedBadges = newlyUnlockedBadges
    )
  }

  suspend fun recordQuizResults(
    node: IslandNode,
    score: Int,
    correctCount: Int,
    totalQuestions: Int,
    drachmasEarned: Int,
    currentProfile: PlayerProfile
  ): QuizCompletionResult {
    val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    val currentDateStr = dateFormat.format(Date())

    val accuracy = if (totalQuestions > 0) (correctCount.toFloat() / totalQuestions.toFloat()) else 0f
    val stars = when {
      accuracy >= 0.99f -> 3
      accuracy >= 0.66f -> 2
      accuracy >= 0.50f -> 1
      else -> 0
    }

    val existingProgress = dao.getNodeProgress(node.id)
    val oldStars = existingProgress?.starsEarned ?: 0
    val newStars = maxOf(oldStars, stars)
    val newHighScore = maxOf(existingProgress?.highScore ?: 0, score)

    dao.insertOrUpdateNodeProgress(
      NodeProgressEntity(
        nodeId = node.id,
        islandId = node.islandId,
        isUnlocked = true,
        starsEarned = newStars,
        highScore = newHighScore,
        completedCount = (existingProgress?.completedCount ?: 0) + 1
      )
    )

    // Unlock subsequent node
    val baseIslands = QuestionBank.getIslands()
    val thisIsland = baseIslands.find { it.id == node.islandId }
    val nextNode = thisIsland?.nodes?.find { it.nodeIndex == node.nodeIndex + 1 }
    if (stars > 0 && nextNode != null) {
      val existingNext = dao.getNodeProgress(nextNode.id)
      dao.insertOrUpdateNodeProgress(
        NodeProgressEntity(
          nodeId = nextNode.id,
          islandId = nextNode.islandId,
          isUnlocked = true,
          starsEarned = existingNext?.starsEarned ?: 0,
          highScore = existingNext?.highScore ?: 0
        )
      )
    }

    val starDiff = maxOf(0, newStars - oldStars)
    val newTotalStars = currentProfile.totalStars + starDiff
    val newDrachmas = currentProfile.drachmas + drachmasEarned
    val newQuestionsAnswered = currentProfile.questionsAnswered + totalQuestions
    val newCorrectCount = currentProfile.correctAnswersCount + correctCount

    dao.insertOrUpdateProfile(
      PlayerProfileEntity(
        id = 1,
        name = currentProfile.name,
        drachmas = newDrachmas,
        xp = currentProfile.xp + score,
        streakDays = currentProfile.streakDays,
        totalStars = newTotalStars,
        questionsAnswered = newQuestionsAnswered,
        correctAnswersCount = newCorrectCount,
        currentIslandId = node.islandId
      )
    )

    // Check badges
    val newlyUnlocked = mutableListOf<String>()
    
    // First voyage badge
    dao.unlockBadge(UnlockedBadgeEntity("badge_first_voyage", currentDateStr))
    
    // Budget master
    if (node.islandId == 1 && node.isBoss && stars >= 2) {
      dao.unlockBadge(UnlockedBadgeEntity("badge_budget_master", currentDateStr))
      newlyUnlocked.add("Spartan Thrifter")
    }

    // Debt slayer
    if (node.islandId == 2 && node.isBoss && stars >= 2) {
      dao.unlockBadge(UnlockedBadgeEntity("badge_debt_slayer", currentDateStr))
      newlyUnlocked.add("Slayer of Scylla")
    }

    // Golden fleece
    if (node.islandId == 3 && node.isBoss && stars >= 2) {
      dao.unlockBadge(UnlockedBadgeEntity("badge_golden_fleece", currentDateStr))
      newlyUnlocked.add("Argonaut of Compounding")
    }

    // Mount Olympus titan
    if (node.islandId == 4 && stars >= 2) {
      dao.unlockBadge(UnlockedBadgeEntity("badge_pantheon_ascendant", currentDateStr))
      newlyUnlocked.add("Titan of Capital")
    }

    // Laurel collector
    if (newTotalStars >= 15) {
      dao.unlockBadge(UnlockedBadgeEntity("badge_laurel_collector", currentDateStr))
      newlyUnlocked.add("Olympian Laurel Crown")
    }

    // Croesus hoard
    if (newDrachmas >= 1000) {
      dao.unlockBadge(UnlockedBadgeEntity("badge_drachma_hoard", currentDateStr))
      newlyUnlocked.add("Treasury of Croesus")
    }

    return QuizCompletionResult(
      starsEarned = stars,
      drachmasEarned = drachmasEarned,
      score = score,
      newlyUnlockedBadges = newlyUnlocked
    )
  }
}

data class QuizCompletionResult(
  val starsEarned: Int,
  val drachmasEarned: Int,
  val score: Int,
  val newlyUnlockedBadges: List<String>
)
