package com.nonofy.game.presentation

sealed class InGameInteraction {
    data class OnPixelClicked(val column: Int, val row: Int): InGameInteraction()
    object ResetBoard: InGameInteraction()
}