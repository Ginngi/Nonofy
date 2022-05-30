package com.nonofy.game.presentation.components.headers

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.nonofy.game.domain.models.Difficulty

fun getHeaderTextSizeFromDifficulty(difficulty: Difficulty): TextUnit {
    return when(difficulty) {
        Difficulty.EASY -> 14.sp
        Difficulty.MEDIUM -> 12.sp
        Difficulty.HARD -> 10.sp
    }
}