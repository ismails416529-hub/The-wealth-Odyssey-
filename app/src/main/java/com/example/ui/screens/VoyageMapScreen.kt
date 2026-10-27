package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Island
import com.example.model.IslandNode
import com.example.model.PlayerProfile
import com.example.ui.theme.DrachmaCoin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldVariant
import com.example.ui.theme.LaurelGreen
import com.example.ui.theme.SpartanRed

@Composable
fun VoyageMapScreen(
  player: PlayerProfile,
  islands: List<Island>,
  onStartQuiz: (IslandNode) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedIslandId by remember { mutableStateOf(1) }

  val activeIsland = islands.find { it.id == selectedIslandId } ?: islands.firstOrNull()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("voyage_map_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Hero Artwork Header
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.wealth_odyssey_hero_1793081239442),
          contentDescription = "The Argonaut Voyage of Wealth Odyssey",
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )
        // Vignette gradient overlay
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                  MaterialTheme.colorScheme.background
                )
              )
            )
        )
        // Text Overlay
        Column(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(16.dp)
        ) {
          Text(
            text = "THE EXPEDITION TO MOUNT OLYMPUS",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = GoldVariant,
            letterSpacing = 1.sp
          )
          Text(
            text = "Navigate the Seas of Capital",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
      }
    }

    // 2. Island Realm Selector Chips
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState())
          .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        islands.forEach { island ->
          val isSelected = (island.id == selectedIslandId)
          val isUnlocked = player.totalStars >= island.requiredStarsToUnlock

          FilterChip(
            modifier = Modifier.testTag("island_chip_${island.id}"),
            selected = isSelected,
            onClick = { selectedIslandId = island.id },
            label = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = island.topic.icon,
                  fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = island.name,
                  fontFamily = FontFamily.Serif,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
                if (!isUnlocked) {
                  Spacer(modifier = Modifier.width(4.dp))
                  Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Locked Island",
                    modifier = Modifier.size(12.dp),
                    tint = MaterialTheme.colorScheme.outline
                  )
                }
              }
            },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
              selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
          )
        }
      }
    }

    // 3. Island Realm Overview Card
    if (activeIsland != null) {
      item {
        val isUnlocked = player.totalStars >= activeIsland.requiredStarsToUnlock

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
          ),
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            if (isUnlocked) GoldPrimary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
          )
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = activeIsland.greekTitle.uppercase(),
                  style = MaterialTheme.typography.labelSmall,
                  color = GoldPrimary,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp
                )
                Text(
                  text = activeIsland.name,
                  style = MaterialTheme.typography.titleLarge,
                  fontFamily = FontFamily.Serif,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }

              // Stars requirement or mastery pill
              if (!isUnlocked) {
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                      imageVector = Icons.Default.Lock,
                      contentDescription = null,
                      tint = MaterialTheme.colorScheme.error,
                      modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                      text = "Need ${activeIsland.requiredStarsToUnlock} ★",
                      style = MaterialTheme.typography.labelSmall,
                      fontWeight = FontWeight.Bold,
                      color = MaterialTheme.colorScheme.error
                    )
                  }
                }
              } else {
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(LaurelGreen.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Text(
                    text = "Charted Realm",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = LaurelGreen
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = activeIsland.description,
              style = MaterialTheme.typography.bodyMedium,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }

      // 4. Sequential Trial Nodes Header
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "EXPEDITION TRIALS",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )
          Text(
            text = "${activeIsland.nodes.count { it.starsEarned > 0 }}/${activeIsland.nodes.size} Completed",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      // 5. The Trial Nodes
      itemsIndexed(activeIsland.nodes) { index, node ->
        TrialNodeCard(
          node = node,
          stepNumber = index + 1,
          isPrecedingCompleted = if (index > 0) activeIsland.nodes[index - 1].starsEarned > 0 else true,
          onClick = { onStartQuiz(node) }
        )
      }
    }

    item {
      Spacer(modifier = Modifier.height(32.dp))
    }
  }
}

@Composable
fun TrialNodeCard(
  node: IslandNode,
  stepNumber: Int,
  isPrecedingCompleted: Boolean,
  onClick: () -> Unit
) {
  val unlocked = node.isUnlocked
  val isBoss = node.isBoss
  val stars = node.starsEarned

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 4.dp)
      .testTag("node_card_${node.id}")
      .clickable(enabled = unlocked, onClick = onClick),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (unlocked) {
        if (isBoss) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
      } else {
        MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
      }
    ),
    border = androidx.compose.foundation.BorderStroke(
      width = if (isBoss && unlocked) 2.dp else 1.dp,
      color = when {
        isBoss && unlocked -> SpartanRed
        stars == 3 -> GoldPrimary
        unlocked -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
      }
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Step Node Avatar / Icon
      Box(
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape)
          .background(
            when {
              !unlocked -> MaterialTheme.colorScheme.surfaceVariant
              isBoss -> SpartanRed.copy(alpha = 0.15f)
              stars > 0 -> GoldPrimary.copy(alpha = 0.2f)
              else -> MaterialTheme.colorScheme.primaryContainer
            }
          )
          .border(
            1.dp,
            if (unlocked) (if (isBoss) SpartanRed else GoldPrimary) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            CircleShape
          ),
        contentAlignment = Alignment.Center
      ) {
        if (!unlocked) {
          Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "Locked",
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(20.dp)
          )
        } else if (isBoss) {
          Text(text = "⚔️", fontSize = 22.sp)
        } else if (stars == 3) {
          Text(text = "👑", fontSize = 22.sp)
        } else {
          Text(
            text = "$stepNumber",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = if (stars > 0) GoldPrimary else MaterialTheme.colorScheme.onSurface
          )
        }
      }

      Spacer(modifier = Modifier.width(14.dp))

      // Node Title & Details
      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          if (isBoss) {
            Text(
              text = "GUARDIAN BOSS • ",
              style = MaterialTheme.typography.labelSmall,
              fontWeight = FontWeight.Bold,
              color = SpartanRed
            )
          }
          Text(
            text = "${node.questions.size} Trials",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Text(
          text = node.title,
          style = MaterialTheme.typography.titleMedium,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = if (unlocked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )

        Text(
          text = node.subtitle,
          style = MaterialTheme.typography.bodySmall,
          color = if (unlocked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )

        // Laurel Stars Row
        if (unlocked) {
          Spacer(modifier = Modifier.height(4.dp))
          Row(verticalAlignment = Alignment.CenterVertically) {
            for (i in 1..3) {
              val isStarEarned = i <= stars
              Icon(
                imageVector = if (isStarEarned) Icons.Filled.Star else Icons.Outlined.StarBorder,
                contentDescription = null,
                tint = if (isStarEarned) GoldPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                modifier = Modifier.size(16.dp)
              )
            }
            if (node.highScore > 0) {
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "${node.highScore} XP",
                style = MaterialTheme.typography.labelSmall,
                color = GoldPrimary,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }
      }

      // Play / Action Indicator
      if (unlocked) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(GoldPrimary)
            .clickable(onClick = onClick),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Embark",
            tint = Color(0xFF1E1500),
            modifier = Modifier.size(20.dp)
          )
        }
      }
    }
  }
}
