package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = GoldPrimary,
  onPrimary = Color(0xFF1E1500),
  primaryContainer = GoldContainerDark,
  onPrimaryContainer = GoldVariant,
  secondary = SeaFoam,
  onSecondary = Color(0xFF032228),
  secondaryContainer = AegeanSeaMedium,
  onSecondaryContainer = Color(0xFFD6F0F5),
  tertiary = LaurelGreen,
  onTertiary = Color.White,
  background = ParchmentDark,
  onBackground = Color(0xFFE8EEF5),
  surface = ParchmentDarkSurface,
  onSurface = Color(0xFFE8EEF5),
  surfaceVariant = ParchmentDarkVariant,
  onSurfaceVariant = Color(0xFFB0C4DE),
  outline = ParchmentDarkBorder,
  error = SpartanRed,
  onError = Color.White
)

private val LightColorScheme = lightColorScheme(
  primary = GoldDark,
  onPrimary = Color.White,
  primaryContainer = GoldContainerLight,
  onPrimaryContainer = Color(0xFF382905),
  secondary = AegeanSeaLight,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFE1F0F5),
  onSecondaryContainer = AegeanSeaDeep,
  tertiary = LaurelGreen,
  onTertiary = Color.White,
  background = ParchmentLight,
  onBackground = AegeanSeaDeep,
  surface = ParchmentSurface,
  onSurface = AegeanSeaDeep,
  surfaceVariant = ParchmentSurfaceVariant,
  onSurfaceVariant = Color(0xFF5A4D3B),
  outline = ParchmentBorder,
  error = SpartanRed,
  onError = Color.White
)

@Composable
fun WealthOdysseyTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
