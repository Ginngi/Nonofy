package com.nonofy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = RedJapanese,
    secondary = RedJapanese,
    background = OldMauve
)

private val LightColorPalette = lightColors(
    primary = RedJapanese,
    secondary = RedJapanese,
    background = OldMauve
)

@Composable
fun NonofyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        DarkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
