package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.DrachmaCoin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldVariant
import com.example.ui.theme.LaurelGreen
import com.example.ui.viewmodel.QuizCelebration

@Composable
fun CelebrationDialog(
  celebration: QuizCelebration,
  onDismiss: () -> Unit
) {
  val result = celebration.result
  val stars = result.starsEarned
  val passed = stars > 0

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .testTag("celebration_dialog"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
      elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Icon header with wreath glow
        Box(
          modifier = Modifier
            .size(72.dp)
            .clip(CircleShape)
            .background(
              Brush.radialGradient(
                colors = if (passed) listOf(GoldVariant, GoldDark) else listOf(Color.LightGray, Color.DarkGray)
              )
            )
            .border(2.dp, GoldPrimary, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.EmojiEvents,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(40.dp)
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Title
        Text(
          text = if (passed) "TRIAL TRIUMPH!" else "THE GODS DEMAND WISDOM",
          style = MaterialTheme.typography.titleLarge,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = if (passed) GoldPrimary else MaterialTheme.colorScheme.error,
          letterSpacing = 1.sp
        )

        Text(
          text = celebration.nodeTitle,
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3 Laurel Stars
        Row(
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically
        ) {
          for (i in 1..3) {
            val earned = i <= stars
            Icon(
              imageVector = if (earned) Icons.Filled.Star else Icons.Outlined.StarBorder,
              contentDescription = null,
              tint = if (earned) GoldPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
              modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(if (i == 2) 44.dp else 36.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Reward Summary Row
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 12.dp, horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceAround,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "🪙 Bounty", style = MaterialTheme.typography.labelSmall)
            Text(
              text = "+${result.drachmasEarned} Drachmas",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DrachmaCoin
            )
          }
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "⚡ XP Score", style = MaterialTheme.typography.labelSmall)
            Text(
              text = "+${result.score} XP",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = GoldPrimary
            )
          }
        }

        // Newly Unlocked Badges
        if (result.newlyUnlockedBadges.isNotEmpty()) {
          Spacer(modifier = Modifier.height(14.dp))
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(LaurelGreen.copy(alpha = 0.12f))
              .border(1.dp, LaurelGreen.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
              .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = "🏅 New Laurel Badge Unlocked!",
              style = MaterialTheme.typography.labelMedium,
              fontWeight = FontWeight.Bold,
              color = LaurelGreen
            )
            result.newlyUnlockedBadges.forEach { badgeName ->
              Text(
                text = "« $badgeName »",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
          onClick = onDismiss,
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("celebration_continue_button"),
          colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(
            text = if (passed) "Claim Spoils & Continue" else "Try Again Later",
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
