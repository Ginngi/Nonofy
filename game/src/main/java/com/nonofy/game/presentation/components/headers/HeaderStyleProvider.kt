package com.nonofy.game.presentation.components.headers

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.nonofy.game.domain.models.Difficulty

fun getHeaderTextSizeFromDifficulty(difficulty: Difficulty): TextUnit {
    return when(difficulty) {
        Difficulty.EASY -> 16.sp
        Difficulty.MEDIUM -> 14.sp
        Difficulty.HARD -> 12.sp
    }
}