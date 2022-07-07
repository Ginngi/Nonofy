package com.nonofy.game.domain.feature

import com.nonofy.game.domain.models.Nonogram

data class InGameModel(
    val isLoading: Boolean,
    val isGameOver: Boolean,
    val isCompletedSuccessfully: Boolean,
    val nonogram: Nonogram
) {
    companion object {
        fun loading() = InGameModel(
            isLoading = true,
            isGameOver = false,
            isCompletedSuccessfully = false,
            nonogram = Nonogram.empty()
        )
    }
}