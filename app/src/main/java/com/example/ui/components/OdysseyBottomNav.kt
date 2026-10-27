package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.MilitaryTech
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.theme.GoldPrimary
import com.example.ui.viewmodel.MainNavTab

@Composable
fun OdysseyBottomNav(
  currentTab: MainNavTab,
  onSelectTab: (MainNavTab) -> Unit,
  modifier: Modifier = Modifier
) {
  NavigationBar(
    modifier = modifier,
    windowInsets = WindowInsets.navigationBars,
    containerColor = MaterialTheme.colorScheme.surface,
    tonalElevation = 8.dp
  ) {
    MainNavTab.values().forEach { tab ->
      val selected = (currentTab == tab)
      val icon = when (tab) {
        MainNavTab.VOYAGE_MAP -> if (selected) Icons.Filled.Explore else Icons.Outlined.Explore
        MainNavTab.PANTHEON -> if (selected) Icons.Filled.Leaderboard else Icons.Outlined.Leaderboard
        MainNavTab.BADGES -> if (selected) Icons.Filled.MilitaryTech else Icons.Outlined.MilitaryTech
        MainNavTab.CODEX -> if (selected) Icons.Filled.MenuBook else Icons.Outlined.MenuBook
      }

      NavigationBarItem(
        modifier = Modifier.testTag("nav_${tab.name.lowercase()}"),
        selected = selected,
        onClick = { onSelectTab(tab) },
        icon = {
          Icon(
            imageVector = icon,
            contentDescription = tab.title
          )
        },
        label = {
          Text(
            text = tab.title,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            fontFamily = FontFamily.Serif
          )
        },
        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
          selectedTextColor = GoldPrimary,
          indicatorColor = MaterialTheme.colorScheme.primaryContainer,
          unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
          unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
      )
    }
  }
}
