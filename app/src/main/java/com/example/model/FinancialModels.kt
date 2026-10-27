package com.example.model

enum class FinancialTopic(val displayName: String, val subtitle: String, val icon: String) {
  BUDGETING("Isle of Budgets", "Oasis of Ithaca & The 50/30/20 Rule", "⚖️"),
  DEBT_MANAGEMENT("Strait of Debt", "The Trials of Scylla & Interest Traps", "🛡️"),
  INVESTING("The Golden Fleece", "Mount Olympus Assets & Compounding", "🌱"),
  BOSS_CHALLENGE("The Olympus Gate", "The Grand Guardian Boss Trial", "⚡")
}

data class QuizQuestion(
  val id: String,
  val topic: FinancialTopic,
  val islandId: Int,
  val nodeIndex: Int,
  val scenarioContext: String? = null,
  val question: String,
  val options: List<String>,
  val correctAnswerIndex: Int,
  val explanation: String,
  val keyTakeaway: String,
  val drachmaReward: Int = 50
)

data class IslandNode(
  val id: String,
  val islandId: Int,
  val nodeIndex: Int,
  val title: String,
  val subtitle: String,
  val topic: FinancialTopic,
  val isBoss: Boolean = false,
  val bossName: String? = null,
  val questions: List<QuizQuestion>,
  val isUnlocked: Boolean = false,
  val starsEarned: Int = 0,
  val highScore: Int = 0
)

data class Island(
  val id: Int,
  val name: String,
  val greekTitle: String,
  val description: String,
  val topic: FinancialTopic,
  val requiredStarsToUnlock: Int,
  val nodes: List<IslandNode>
)

data class BadgeItem(
  val id: String,
  val title: String,
  val greekTitle: String,
  val description: String,
  val category: String,
  val emojiIcon: String,
  val isUnlocked: Boolean = false,
  val unlockedDate: String? = null,
  val currentProgress: Int = 0,
  val maxProgress: Int = 1
)

data class PantheonRival(
  val rank: Int,
  val name: String,
  val title: String,
  val drachmas: Int,
  val stars: Int,
  val isPlayer: Boolean = false,
  val symbol: String,
  val epithet: String
)

data class PlayerProfile(
  val name: String = "Voyager",
  val title: String = "Novice Mariner",
  val drachmas: Int = 100,
  val xp: Int = 0,
  val totalStars: Int = 0,
  val streakDays: Int = 1,
  val currentIslandId: Int = 1,
  val questionsAnswered: Int = 0,
  val correctAnswersCount: Int = 0
) {
  val accuracyPercentage: Int
    get() = if (questionsAnswered > 0) (correctAnswersCount * 100) / questionsAnswered else 0

  val rankTitle: String
    get() = when {
      drachmas >= 2500 -> "Olympian Titan of Wealth"
      drachmas >= 1500 -> "Argonaut Explorer"
      drachmas >= 800 -> "Hellenic Quartermaster"
      drachmas >= 400 -> "Prudent Navigator"
      else -> "Novice Mariner"
    }
}

data class QuizSessionState(
  val node: IslandNode,
  val currentQuestionIndex: Int = 0,
  val selectedAnswerIndex: Int? = null,
  val isAnswerSubmitted: Boolean = false,
  val score: Int = 0,
  val correctAnswersCount: Int = 0,
  val drachmasEarned: Int = 0,
  val isCompleted: Boolean = false,
  val starsEarned: Int = 0
) {
  val currentQuestion: QuizQuestion?
    get() = node.questions.getOrNull(currentQuestionIndex)

  val isLastQuestion: Boolean
    get() = currentQuestionIndex >= node.questions.size - 1

  val progressFraction: Float
    get() = if (node.questions.isNotEmpty()) {
      (currentQuestionIndex + 1).toFloat() / node.questions.size.toFloat()
    } else 0f
}
