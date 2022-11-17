package com.nonofy.game.impl.presentation.components.headers

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.nonofy.game.impl.domain.models.Difficulty

fun getHeaderTextSizeFromDifficulty(difficulty: Difficulty): TextUnit {
    return when (difficulty) {
        Difficulty.EASY -> 16.sp
        Difficulty.MEDIUM -> 14.sp
        Difficulty.HARD -> 12.sp
    }
}

@Composable
fun getAnimatedBackground(isCompleted: Boolean) = animateColorAsState(
    targetValue = if (isCompleted) MaterialTheme.colors.background else MaterialTheme.colors.primary,
    animationSpec = tween(
        durationMillis = 300,
        delayMillis = 50,
        easing = LinearOutSlowInEasing
    )
)

@Composable
fun getHeaderTextColor(isHeaderCompleted: Boolean, isLineCompleted: Boolean): Color {
    val textColor = if (isHeaderCompleted) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.background
    }
    
    return textColor.copy(alpha = if (isLineCompleted) 0.6f else 1f)
}
