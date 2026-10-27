package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.repository.QuizCompletionResult
import com.example.data.repository.WealthOdysseyRepository
import com.example.model.BadgeItem
import com.example.model.Island
import com.example.model.IslandNode
import com.example.model.PantheonRival
import com.example.model.PlayerProfile
import com.example.model.QuizSessionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class MainNavTab(val title: String, val iconName: String) {
  VOYAGE_MAP("Voyage Map", "map"),
  PANTHEON("Pantheon", "leaderboard"),
  BADGES("Badges", "medal"),
  CODEX("Oracle Codex", "book")
}

data class QuizCelebration(
  val result: QuizCompletionResult,
  val nodeTitle: String
)

class WealthOdysseyViewModel(application: Application) : AndroidViewModel(application) {

  private val repository: WealthOdysseyRepository

  init {
    val db = AppDatabase.getDatabase(application)
    repository = WealthOdysseyRepository(db.odysseyDao())
    viewModelScope.launch {
      repository.initializeIfNeeded()
    }
  }

  val playerProfile: StateFlow<PlayerProfile> = repository.playerProfile
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = PlayerProfile()
    )

  val islands: StateFlow<List<Island>> = repository.islands
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  val badges: StateFlow<List<BadgeItem>> = repository.badges
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  val pantheon: StateFlow<List<PantheonRival>> = repository.pantheon
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  private val _currentTab = MutableStateFlow(MainNavTab.VOYAGE_MAP)
  val currentTab: StateFlow<MainNavTab> = _currentTab.asStateFlow()

  private val _activeQuizSession = MutableStateFlow<QuizSessionState?>(null)
  val activeQuizSession: StateFlow<QuizSessionState?> = _activeQuizSession.asStateFlow()

  private val _celebrationDialog = MutableStateFlow<QuizCelebration?>(null)
  val celebrationDialog: StateFlow<QuizCelebration?> = _celebrationDialog.asStateFlow()

  fun setTab(tab: MainNavTab) {
    _currentTab.value = tab
  }

  fun startQuiz(node: IslandNode) {
    if (!node.isUnlocked) return
    _activeQuizSession.value = QuizSessionState(
      node = node,
      currentQuestionIndex = 0,
      selectedAnswerIndex = null,
      isAnswerSubmitted = false,
      score = 0,
      correctAnswersCount = 0,
      drachmasEarned = 0,
      isCompleted = false,
      starsEarned = 0
    )
  }

  fun selectAnswer(index: Int) {
    val current = _activeQuizSession.value ?: return
    if (current.isAnswerSubmitted) return
    _activeQuizSession.value = current.copy(selectedAnswerIndex = index)
  }

  fun submitCurrentAnswer() {
    val current = _activeQuizSession.value ?: return
    val question = current.currentQuestion ?: return
    val selected = current.selectedAnswerIndex ?: return
    if (current.isAnswerSubmitted) return

    val isCorrect = (selected == question.correctAnswerIndex)
    val addedScore = if (isCorrect) 100 else 0
    val addedDrachmas = if (isCorrect) question.drachmaReward else 10 // consolation drachmas for trying!
    val newCorrectCount = current.correctAnswersCount + (if (isCorrect) 1 else 0)

    _activeQuizSession.value = current.copy(
      isAnswerSubmitted = true,
      score = current.score + addedScore,
      drachmasEarned = current.drachmasEarned + addedDrachmas,
      correctAnswersCount = newCorrectCount
    )
  }

  fun nextQuestionOrFinish() {
    val current = _activeQuizSession.value ?: return
    if (!current.isAnswerSubmitted) return

    if (current.isLastQuestion) {
      // Finish quiz session
      viewModelScope.launch {
        val profile = playerProfile.value
        val result = repository.recordQuizResults(
          node = current.node,
          score = current.score,
          correctCount = current.correctAnswersCount,
          totalQuestions = current.node.questions.size,
          drachmasEarned = current.drachmasEarned,
          currentProfile = profile
        )
        _activeQuizSession.value = null
        _celebrationDialog.value = QuizCelebration(
          result = result,
          nodeTitle = current.node.title
        )
      }
    } else {
      // Proceed to next question
      _activeQuizSession.value = current.copy(
        currentQuestionIndex = current.currentQuestionIndex + 1,
        selectedAnswerIndex = null,
        isAnswerSubmitted = false
      )
    }
  }

  fun dismissCelebration() {
    _celebrationDialog.value = null
  }

  fun exitQuiz() {
    _activeQuizSession.value = null
  }
}
