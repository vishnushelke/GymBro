package com.pamu.gymbro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = EnergyOrange,
    onPrimary = Color.Black,
    secondary = EnergyYellow,
    onSecondary = Color.Black,
    tertiary = Color.Cyan,
    background = DeepCharcoal,
    onBackground = TextPrimary,
    surface = CardBackground,
    onSurface = TextPrimary,
    error = Color(0xFFCF6679),
    onError = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = EnergyOrange,
    onPrimary = Color.White,
    secondary = EnergyYellow,
    onSecondary = Color.Black,
    tertiary = Color.Blue,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color(0xFFF5F5F5),
    onSurface = Color.Black
)

@Composable
fun GymBroTheme(
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
