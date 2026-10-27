package com.example.ui.components

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PlayerProfile
import com.example.ui.theme.DrachmaCoin
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldVariant

@Composable
fun OdysseyTopBar(
  player: PlayerProfile,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .statusBarsPadding(),
    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
    tonalElevation = 4.dp
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // App Title & Voyager Rank
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(38.dp)
              .clip(CircleShape)
              .background(
                Brush.radialGradient(
                  colors = listOf(GoldVariant, GoldPrimary)
                )
              )
              .border(1.5.dp, GoldPrimary, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "🔱",
              fontSize = 20.sp
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "WEALTH ODYSSEY",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Serif,
              color = MaterialTheme.colorScheme.onSurface,
              letterSpacing = 1.sp
            )
            Text(
              text = player.rankTitle,
              style = MaterialTheme.typography.labelSmall,
              color = GoldPrimary,
              fontWeight = FontWeight.SemiBold
            )
          }
        }

        // Stats Badges (Drachmas, Stars, Streak)
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          // Drachma Balance Pill
          StatPill(
            icon = "🪙",
            label = "${player.drachmas}",
            accentColor = DrachmaCoin,
            testTag = "drachma_counter"
          )

          // Laurel Stars Pill
          StatPill(
            iconVector = Icons.Default.Star,
            label = "${player.totalStars}",
            accentColor = GoldPrimary,
            testTag = "star_counter"
          )

          // Daily Streak
          StatPill(
            icon = "⚡",
            label = "${player.streakDays}d",
            accentColor = Color(0xFFE67E22),
            testTag = "streak_counter"
          )
        }
      }
    }
  }
}

@Composable
private fun StatPill(
  modifier: Modifier = Modifier,
  icon: String? = null,
  iconVector: androidx.compose.ui.graphics.vector.ImageVector? = null,
  label: String,
  accentColor: Color,
  testTag: String
) {
  Box(
    modifier = modifier
      .testTag(testTag)
      .clip(RoundedCornerShape(16.dp))
      .background(MaterialTheme.colorScheme.surfaceVariant)
      .border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
      .padding(horizontal = 8.dp, vertical = 4.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      if (icon != null) {
        Text(text = icon, fontSize = 13.sp)
        Spacer(modifier = Modifier.width(4.dp))
      } else if (iconVector != null) {
        Icon(
          imageVector = iconVector,
          contentDescription = null,
          tint = accentColor,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(3.dp))
      }
      Text(
        text = label,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )
    }
  }
}
