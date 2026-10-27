package com.example.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BadgeItem
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldVariant
import com.example.ui.theme.LaurelGreen

@Composable
fun BadgesScreen(
  badges: List<BadgeItem>,
  modifier: Modifier = Modifier
) {
  var selectedCategory by remember { mutableStateOf("All") }
  var inspectingBadge by remember { mutableStateOf<BadgeItem?>(null) }

  val categories = listOf("All", "Voyage", "Budgeting", "Debt", "Investing", "Legendary")
  val filteredBadges = if (selectedCategory == "All") {
    badges
  } else {
    badges.filter { it.category.equals(selectedCategory, ignoreCase = true) }
  }

  val unlockedCount = badges.count { it.isUnlocked }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp)
      .testTag("badges_screen"),
    verticalArrangement = Arrangement.spacedBy(14.dp)
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
          text = "👑 TREASURY OF LAUREL MEDALLIONS",
          style = MaterialTheme.typography.labelSmall,
          fontWeight = FontWeight.Bold,
          color = GoldPrimary,
          letterSpacing = 1.sp
        )
        Text(
          text = "Honors of the Argonaut",
          style = MaterialTheme.typography.headlineSmall,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )
        Text(
          text = "$unlockedCount of ${badges.size} Mythic Medallions Claimed",
          style = MaterialTheme.typography.bodyMedium,
          fontWeight = FontWeight.SemiBold,
          color = LaurelGreen
        )
      }
    }

    // 2. Category Filters
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        categories.forEach { category ->
          val isSelected = selectedCategory == category
          FilterChip(
            selected = isSelected,
            onClick = { selectedCategory = category },
            label = {
              Text(
                text = category,
                fontFamily = FontFamily.Serif,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
              )
            },
            colors = FilterChipDefaults.filterChipColors(
              selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
              selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
          )
        }
      }
    }

    // 3. Badges List
    items(filteredBadges) { badge ->
      BadgeRowCard(
        badge = badge,
        onClick = { inspectingBadge = badge }
      )
    }

    item {
      Spacer(modifier = Modifier.height(24.dp))
    }
  }

  // Detail Modal
  if (inspectingBadge != null) {
    val b = inspectingBadge!!
    AlertDialog(
      onDismissRequest = { inspectingBadge = null },
      confirmButton = {
        TextButton(onClick = { inspectingBadge = null }) {
          Text(text = "Close Scroll", color = GoldPrimary, fontWeight = FontWeight.Bold)
        }
      },
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = b.emojiIcon, fontSize = 28.sp)
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = b.title,
              fontFamily = FontFamily.Serif,
              fontWeight = FontWeight.Bold,
              style = MaterialTheme.typography.titleLarge
            )
            Text(
              text = "« ${b.greekTitle} »",
              style = MaterialTheme.typography.labelSmall,
              fontStyle = FontStyle.Italic,
              color = GoldPrimary
            )
          }
        }
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = b.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
          )
          Spacer(modifier = Modifier.height(4.dp))
          if (b.isUnlocked) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(LaurelGreen.copy(alpha = 0.15f))
                .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
              Text(
                text = "Awarded on ${b.unlockedDate ?: "Odyssey Voyage"}",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = LaurelGreen
              )
            }
          } else {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
              Text(
                text = "Locked: Progress along the island map to earn this tribute.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    )
  }
}

@Composable
fun BadgeRowCard(
  badge: BadgeItem,
  onClick: () -> Unit
) {
  val unlocked = badge.isUnlocked

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("badge_card_${badge.id}")
      .clickable(onClick = onClick),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (unlocked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
    ),
    border = androidx.compose.foundation.BorderStroke(
      width = if (unlocked) 1.5.dp else 1.dp,
      color = if (unlocked) GoldPrimary else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Medallion Symbol
      Box(
        modifier = Modifier
          .size(52.dp)
          .clip(CircleShape)
          .background(
            if (unlocked) {
              Brush.radialGradient(listOf(GoldVariant, GoldDark))
            } else {
              Brush.radialGradient(listOf(Color(0xFF4A4A4A), Color(0xFF2C2C2C)))
            }
          )
          .border(
            1.5.dp,
            if (unlocked) GoldPrimary else Color.Gray,
            CircleShape
          ),
        contentAlignment = Alignment.Center
      ) {
        if (unlocked) {
          Text(text = badge.emojiIcon, fontSize = 26.sp)
        } else {
          Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = "Locked",
            tint = Color.LightGray,
            modifier = Modifier.size(22.dp)
          )
        }
      }

      Spacer(modifier = Modifier.width(14.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = badge.category.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = if (unlocked) GoldPrimary else MaterialTheme.colorScheme.onSurfaceVariant
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "« ${badge.greekTitle} »",
            style = MaterialTheme.typography.labelSmall,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Text(
          text = badge.title,
          style = MaterialTheme.typography.titleMedium,
          fontFamily = FontFamily.Serif,
          fontWeight = FontWeight.Bold,
          color = if (unlocked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.45f)
        )

        Text(
          text = badge.description,
          style = MaterialTheme.typography.bodySmall,
          color = if (unlocked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
          maxLines = 2
        )
      }

      if (unlocked) {
        Box(
          modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(LaurelGreen),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Claimed",
            tint = Color.White,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }
  }
}
