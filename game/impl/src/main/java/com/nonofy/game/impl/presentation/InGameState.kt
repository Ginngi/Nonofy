package com.nonofy.game.impl.presentation

import com.nonofy.game.impl.presentation.components.ingameboard.InGameBoardState

data class InGameState(
    val title: String = "",
    val numErrors: Int = 0,
    val isLoading: Boolean = false,
    val isGameOver: Boolean = false,
    val isCompletedSuccessfully: Boolean = false,
    val inGameBoardState: InGameBoardState? = null,
    val isPixelModeEnabled: Boolean = true
) {
    companion object {
        fun loading() = InGameState(isLoading = true)
    }
}