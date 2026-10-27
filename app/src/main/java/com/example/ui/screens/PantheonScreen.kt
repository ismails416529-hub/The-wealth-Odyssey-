package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Star
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
import com.example.model.PantheonRival
import com.example.model.PlayerProfile
import com.example.ui.theme.DrachmaCoin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldVariant
import com.example.ui.theme.LaurelGreen

@Composable
fun PantheonScreen(
  player: PlayerProfile,
  pantheonList: List<PantheonRival>,
  modifier: Modifier = Modifier
) {
  val topThree = pantheonList.take(3)
  val remaining = pantheonList.drop(3)
  val playerRank = pantheonList.find { it.isPlayer }?.rank ?: 1

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp)
      .testTag("pantheon_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Header
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "🏛️ MOUNT OLYMPUS PANTHEON",
          style = MaterialTheme.typography.labelSmall,
          fontWeight = FontWeight.Bold,
          color = GoldPrimary,
          letterSpacing = 1.sp
        )
        Text(
          text = "The Hall of Wealth Champions",
          style = MaterialTheme.typography.headlineSmall,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )
        Text(
          text = "Ascend by conquering trials, saving drachmas, and compounding wisdom.",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center
        )
      }
    }

    // 2. Podium (1st, 2nd, 3rd)
    if (topThree.size >= 3) {
      item {
        PodiumView(topThree = topThree)
      }
    }

    // 3. Player's Live Standing Card
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("player_standing_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        border = androidx.compose.foundation.BorderStroke(2.dp, GoldPrimary)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(46.dp)
              .clip(CircleShape)
              .background(GoldPrimary)
              .border(1.5.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Text(text = "#$playerRank", fontWeight = FontWeight.Bold, color = Color(0xFF1E1500))
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "${player.name} (You)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "🔱",
                fontSize = 14.sp
              )
            }
            Text(
              text = player.rankTitle,
              style = MaterialTheme.typography.labelSmall,
              color = GoldDark,
              fontWeight = FontWeight.SemiBold
            )
          }

          Column(horizontalAlignment = Alignment.End) {
            Text(
              text = "🪙 ${player.drachmas}",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = DrachmaCoin
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = GoldPrimary,
                modifier = Modifier.size(13.dp)
              )
              Spacer(modifier = Modifier.width(2.dp))
              Text(
                text = "${player.totalStars} Laurels",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
              )
            }
          }
        }
      }
    }

    // 4. Section Label
    item {
      Text(
        text = "PANTHEON COMPETITORS",
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        letterSpacing = 1.sp
      )
    }

    // 5. Remaining Competitors List
    items(remaining) { rival ->
      RivalRow(rival = rival)
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }
}

@Composable
fun PodiumView(topThree: List<PantheonRival>) {
  val first = topThree[0]
  val second = topThree[1]
  val third = topThree[2]

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.Bottom
  ) {
    // 2nd Place (Left)
    PodiumStep(
      rival = second,
      rank = 2,
      medalColor = Color(0xFFC0C0C0), // Silver
      heightDp = 100
    )

    // 1st Place (Center, Tallest)
    PodiumStep(
      rival = first,
      rank = 1,
      medalColor = GoldPrimary, // Gold
      heightDp = 130
    )

    // 3rd Place (Right)
    PodiumStep(
      rival = third,
      rank = 3,
      medalColor = Color(0xFFCD7F32), // Bronze
      heightDp = 80
    )
  }
}

@Composable
fun PodiumStep(
  rival: PantheonRival,
  rank: Int,
  medalColor: Color,
  heightDp: Int
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.width(100.dp)
  ) {
    // Avatar with Crown
    Box(
      modifier = Modifier
        .size(if (rank == 1) 56.dp else 48.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .border(2.dp, medalColor, CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = rival.symbol,
        fontSize = if (rank == 1) 28.sp else 22.sp
      )
    }

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = rival.name,
      style = MaterialTheme.typography.labelMedium,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onBackground,
      maxLines = 1
    )

    Text(
      text = "🪙 ${rival.drachmas}",
      style = MaterialTheme.typography.bodySmall,
      fontWeight = FontWeight.Bold,
      color = DrachmaCoin
    )

    Spacer(modifier = Modifier.height(6.dp))

    // Podium Block
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(heightDp.dp)
        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        .background(
          Brush.verticalGradient(
            colors = listOf(
              medalColor.copy(alpha = 0.45f),
              MaterialTheme.colorScheme.surfaceVariant
            )
          )
        )
        .border(
          1.dp,
          medalColor.copy(alpha = 0.6f),
          RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        ),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = "#$rank",
        style = MaterialTheme.typography.headlineMedium,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        color = medalColor
      )
    }
  }
}

@Composable
fun RivalRow(rival: PantheonRival) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (rival.isPlayer) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
    ),
    border = androidx.compose.foundation.BorderStroke(
      1.dp,
      if (rival.isPlayer) GoldPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "#${rival.rank}",
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.width(32.dp)
      )

      Box(
        modifier = Modifier
          .size(38.dp)
          .clip(CircleShape)
          .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
      ) {
        Text(text = rival.symbol, fontSize = 18.sp)
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = rival.name,
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = rival.epithet,
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 1
        )
      }

      Column(horizontalAlignment = Alignment.End) {
        Text(
          text = "🪙 ${rival.drachmas}",
          style = MaterialTheme.typography.labelLarge,
          fontWeight = FontWeight.Bold,
          color = DrachmaCoin
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = GoldPrimary,
            modifier = Modifier.size(12.dp)
          )
          Spacer(modifier = Modifier.width(2.dp))
          Text(
            text = "${rival.stars}★",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }
  }
}
