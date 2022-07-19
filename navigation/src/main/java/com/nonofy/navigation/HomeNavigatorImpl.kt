package com.nonofy.navigation

import com.nonofy.home.presentation.HomeNavigator
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(
    private val navigatorManager: NavigatorManager
) : HomeNavigator {
    override fun navigateToGame(gameId: String) {
        navigatorManager.navigate(Destinations.InGame(gameId))
    }

    override fun navigateToCreativeMode() {
        navigatorManager.navigate(Destinations.Creative)
    }

    override fun navigateToSavedGame() {
        navigatorManager.navigate(Destinations.InGame("saved"))
    }
}