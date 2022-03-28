package com.nonofy.game.presentation

import com.nonofy.game.presentation.components.ingameboard.InGameBoardState

data class InGameState(
    val title: String = "",
    val numErrors: Int = 0,
    val isLoading: Boolean = false,
    val isGameOver: Boolean = false,
    val inGameBoardState: InGameBoardState? = null
) {
    companion object {
        fun loading() = InGameState(isLoading = true)
    }
}