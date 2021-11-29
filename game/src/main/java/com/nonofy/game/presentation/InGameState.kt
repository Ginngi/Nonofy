package com.nonofy.game.presentation

import com.nonofy.game.presentation.components.ingameboard.InGameBoardState

sealed class InGameState {
    data class ShowingInGameBoard(
        val numErrors: Int = 0,
        val inGameBoardState: InGameBoardState
    ) : InGameState()

    object LoadingGame : InGameState()
}