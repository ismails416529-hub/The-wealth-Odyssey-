package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.QuizSessionState
import com.example.ui.theme.DrachmaCoin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.LaurelGreen
import com.example.ui.theme.SpartanRed

@Composable
fun QuizPlayScreen(
  session: QuizSessionState,
  onSelectOption: (Int) -> Unit,
  onSubmitAnswer: () -> Unit,
  onNextOrFinish: () -> Unit,
  onExit: () -> Unit,
  modifier: Modifier = Modifier
) {
  val question = session.currentQuestion ?: return
  val selectedIndex = session.selectedAnswerIndex
  val isSubmitted = session.isAnswerSubmitted
  val isCorrect = selectedIndex == question.correctAnswerIndex

  Surface(
    modifier = modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
        .testTag("quiz_play_screen")
    ) {
      // 1. Top Bar
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = onExit,
          modifier = Modifier.testTag("quiz_exit_button")
        ) {
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Exit Trial",
            tint = MaterialTheme.colorScheme.onSurface
          )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = session.node.title.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = GoldPrimary,
            letterSpacing = 1.sp
          )
          Text(
            text = "Trial ${session.currentQuestionIndex + 1} of ${session.node.questions.size}",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          )
        }

        // Drachmas Bounty Tag
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, DrachmaCoin.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(
            text = "🪙 +${question.drachmaReward}",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = DrachmaCoin
          )
        }
      }

      // Progress Bar
      LinearProgressIndicator(
        progress = { session.progressFraction },
        modifier = Modifier
          .fillMaxWidth()
          .height(5.dp),
        color = GoldPrimary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant
      )

      // 2. Scrollable Question Content
      Column(
        modifier = Modifier
          .weight(1f)
          .verticalScroll(rememberScrollState())
          .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Scenario Context Box (if provided)
        if (!question.scenarioContext.isNullOrBlank()) {
          Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
              containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
            ),
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              GoldPrimary.copy(alpha = 0.3f)
            )
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.Top
            ) {
              Text(text = "📜", fontSize = 20.sp)
              Spacer(modifier = Modifier.width(10.dp))
              Column {
                Text(
                  text = "EXPEDITION SCENARIO",
                  style = MaterialTheme.typography.labelSmall,
                  fontWeight = FontWeight.Bold,
                  color = GoldPrimary,
                  letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = question.scenarioContext,
                  style = MaterialTheme.typography.bodyMedium,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            }
          }
        }

        // The Question Card
        Text(
          text = question.question,
          style = MaterialTheme.typography.titleLarge,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface,
          lineHeight = 28.sp
        )

        // Options List
        val letters = listOf("A", "B", "C", "D")
        question.options.forEachIndexed { index, optionText ->
          val isSelected = selectedIndex == index
          val isThisCorrect = index == question.correctAnswerIndex

          val backgroundColor = when {
            isSubmitted && isThisCorrect -> LaurelGreen.copy(alpha = 0.15f)
            isSubmitted && isSelected && !isThisCorrect -> SpartanRed.copy(alpha = 0.15f)
            isSelected -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.surface
          }

          val borderColor = when {
            isSubmitted && isThisCorrect -> LaurelGreen
            isSubmitted && isSelected && !isThisCorrect -> SpartanRed
            isSelected -> GoldPrimary
            else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
          }

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .testTag("quiz_option_$index")
              .clickable(enabled = !isSubmitted) { onSelectOption(index) },
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            border = androidx.compose.foundation.BorderStroke(
              width = if (isSelected || (isSubmitted && isThisCorrect)) 2.dp else 1.dp,
              color = borderColor
            )
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              // Letter Badge
              Box(
                modifier = Modifier
                  .size(32.dp)
                  .clip(CircleShape)
                  .background(
                    when {
                      isSubmitted && isThisCorrect -> LaurelGreen
                      isSubmitted && isSelected && !isThisCorrect -> SpartanRed
                      isSelected -> GoldPrimary
                      else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                  ),
                contentAlignment = Alignment.Center
              ) {
                if (isSubmitted && isThisCorrect) {
                  Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                  )
                } else {
                  Text(
                    text = letters.getOrElse(index) { "${index + 1}" },
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color(0xFF1E1500) else MaterialTheme.colorScheme.onSurface
                  )
                }
              }

              Spacer(modifier = Modifier.width(12.dp))

              Text(
                text = optionText,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
              )
            }
          }
        }

        // Wisdom of Athena (Feedback Reveal)
        AnimatedVisibility(
          visible = isSubmitted,
          enter = fadeIn() + expandVertically()
        ) {
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .testTag("quiz_explanation_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
              containerColor = if (isCorrect) LaurelGreen.copy(alpha = 0.1f) else SpartanRed.copy(alpha = 0.08f)
            ),
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              if (isCorrect) LaurelGreen.copy(alpha = 0.5f) else SpartanRed.copy(alpha = 0.5f)
            )
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = if (isCorrect) "🦉" else "🛡️", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = if (isCorrect) "WISDOM OF ATHENA: MASTERFUL!" else "ATHENIAN CORRECTION",
                  style = MaterialTheme.typography.titleMedium,
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold,
                  color = if (isCorrect) LaurelGreen else SpartanRed
                )
              }

              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = question.explanation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 22.sp
              )

              Spacer(modifier = Modifier.height(10.dp))

              // Golden Key Takeaway Pill
              Box(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(8.dp))
                  .background(GoldPrimary.copy(alpha = 0.15f))
                  .border(1.dp, GoldPrimary.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                  .padding(8.dp)
              ) {
                Row(verticalAlignment = Alignment.Top) {
                  Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = GoldPrimary,
                    modifier = Modifier.size(16.dp)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "Odyssey Maxim: ${question.keyTakeaway}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                }
              }
            }
          }
        }
      }

      // 3. Bottom Action Bar
      Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
          if (!isSubmitted) {
            Button(
              onClick = onSubmitAnswer,
              enabled = selectedIndex != null,
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("quiz_submit_button"),
              colors = ButtonDefaults.buttonColors(
                containerColor = GoldPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant
              ),
              shape = RoundedCornerShape(12.dp)
            ) {
              Text(
                text = "Consult the Oracle",
                style = MaterialTheme.typography.labelLarge,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = if (selectedIndex != null) Color(0xFF1E1500) else MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          } else {
            Button(
              onClick = onNextOrFinish,
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("quiz_next_button"),
              colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary),
              shape = RoundedCornerShape(12.dp)
            ) {
              Text(
                text = if (session.isLastQuestion) "Complete Trial 👑" else "Next Trial →",
                style = MaterialTheme.typography.labelLarge,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1500)
              )
            }
          }
        }
      }
    }
  }
}
