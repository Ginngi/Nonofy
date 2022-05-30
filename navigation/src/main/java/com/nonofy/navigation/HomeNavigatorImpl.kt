package com.nonofy.navigation

import com.nonofy.home.presentation.HomeNavigator
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(
    private val navigatorManager: NavigatorManager
) : HomeNavigator {
    override fun navigateToGame() {
        navigatorManager.navigate(Destinations.InGame)
    }

    override fun navigateToCreativeMode() {
        navigatorManager.navigate(Destinations.Creative)
    }
}