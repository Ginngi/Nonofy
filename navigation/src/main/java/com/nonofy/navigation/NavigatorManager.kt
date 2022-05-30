package com.nonofy.navigation

import androidx.navigation.NavController
import javax.inject.Inject

class NavigatorManager @Inject constructor() {
    private lateinit var navController: NavController

    fun init(navController: NavController) {
        this.navController = navController
    }

    fun navigate(directions: Destinations) {
        navController.navigate(directions.name)
    }
}