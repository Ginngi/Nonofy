package com.nonofy.game.impl.domain.feature

import com.nonofy.game.impl.domain.models.Nonogram

data class InGameModel(
    val isLoading: Boolean,
    val isGameOver: Boolean,
    val isCompletedSuccessfully: Boolean,
    val nonogram: Nonogram,
    val isPixelEnabled: Boolean
) {
    companion object {
        fun loading() = InGameModel(
            isLoading = true,
            isGameOver = false,
            isCompletedSuccessfully = false,
            nonogram = Nonogram.empty(),
            isPixelEnabled = true
        )
    }
}