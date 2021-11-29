package com.nonofy.game.presentation

sealed class InGameSideEffect {
    object GameOver : InGameSideEffect()
    object Empty: InGameSideEffect()
}