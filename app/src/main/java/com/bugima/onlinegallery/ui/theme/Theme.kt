package com.bugima.onlinegallery.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Accent,
    onPrimary = LightPrimary,
    primaryContainer = AccentDark,
    onPrimaryContainer = AccentLight,
    background = DarkPrimary,
    onBackground = LightPrimary,
    surface = DarkSecondary,
    onSurface = LightPrimary,
    error = Error,
    onError = LightPrimary,
    errorContainer = ErrorDark,
    onErrorContainer = ErrorLight,
)

private val LightColorScheme = lightColorScheme(
    primary = Accent,
    onPrimary = DarkPrimary,
    primaryContainer = AccentLight,
    onPrimaryContainer = AccentDark,
    background = LightSecondary,
    onBackground = DarkPrimary,
    surface = LightPrimary,
    onSurface = DarkSecondary,
    error = Error,
    onError = LightPrimary,
    errorContainer = ErrorLight,
    onErrorContainer = ErrorDark,
)

@Composable
fun OnlineGalleryTheme(
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