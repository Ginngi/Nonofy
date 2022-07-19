package com.nonofy.home.presentation

interface HomeNavigator {
    fun navigateToGame(gameId: String)
    fun navigateToSavedGame()
    fun navigateToCreativeMode()
}