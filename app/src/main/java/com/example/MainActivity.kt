package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.OdysseyBottomNav
import com.example.ui.components.OdysseyTopBar
import com.example.ui.screens.BadgesScreen
import com.example.ui.screens.CelebrationDialog
import com.example.ui.screens.CodexScreen
import com.example.ui.screens.PantheonScreen
import com.example.ui.screens.QuizPlayScreen
import com.example.ui.screens.VoyageMapScreen
import com.example.ui.theme.WealthOdysseyTheme
import com.example.ui.viewmodel.MainNavTab
import com.example.ui.viewmodel.WealthOdysseyViewModel

class MainActivity : ComponentActivity() {

  private val viewModel: WealthOdysseyViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      WealthOdysseyTheme {
        WealthOdysseyApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun WealthOdysseyApp(viewModel: WealthOdysseyViewModel) {
  val player by viewModel.playerProfile.collectAsStateWithLifecycle()
  val islands by viewModel.islands.collectAsStateWithLifecycle()
  val badges by viewModel.badges.collectAsStateWithLifecycle()
  val pantheon by viewModel.pantheon.collectAsStateWithLifecycle()
  val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
  val activeQuiz by viewModel.activeQuizSession.collectAsStateWithLifecycle()
  val celebration by viewModel.celebrationDialog.collectAsStateWithLifecycle()

  // Handle system back navigation
  BackHandler(enabled = activeQuiz != null || currentTab != MainNavTab.VOYAGE_MAP) {
    if (activeQuiz != null) {
      viewModel.exitQuiz()
    } else {
      viewModel.setTab(MainNavTab.VOYAGE_MAP)
    }
  }

  Box(modifier = Modifier.fillMaxSize()) {
    if (activeQuiz != null) {
      // Fullscreen Quiz Experience
      QuizPlayScreen(
        session = activeQuiz!!,
        onSelectOption = { viewModel.selectAnswer(it) },
        onSubmitAnswer = { viewModel.submitCurrentAnswer() },
        onNextOrFinish = { viewModel.nextQuestionOrFinish() },
        onExit = { viewModel.exitQuiz() }
      )
    } else {
      // Main App Scaffold
      Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
          OdysseyTopBar(player = player)
        },
        bottomBar = {
          OdysseyBottomNav(
            currentTab = currentTab,
            onSelectTab = { viewModel.setTab(it) }
          )
        }
      ) { innerPadding ->
        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
          when (currentTab) {
            MainNavTab.VOYAGE_MAP -> {
              VoyageMapScreen(
                player = player,
                islands = islands,
                onStartQuiz = { viewModel.startQuiz(it) }
              )
            }
            MainNavTab.PANTHEON -> {
              PantheonScreen(
                player = player,
                pantheonList = pantheon
              )
            }
            MainNavTab.BADGES -> {
              BadgesScreen(
                badges = badges
              )
            }
            MainNavTab.CODEX -> {
              CodexScreen()
            }
          }
        }
      }
    }

    // Celebration modal when trial completed
    if (celebration != null) {
      CelebrationDialog(
        celebration = celebration!!,
        onDismiss = { viewModel.dismissCelebration() }
      )
    }
  }
}
