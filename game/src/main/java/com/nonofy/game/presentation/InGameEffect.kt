package com.nonofy.game.presentation

sealed class InGameEffect {
    data class UpdatePixelStateAtPosition(val column: Int, val row: Int): InGameEffect()
    object CreateEmptyBoard : InGameEffect()
}