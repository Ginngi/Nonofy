package com.nonofy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nonofy.creative.CreativeScreen
import com.nonofy.game.presentation.InGameScreen
import com.nonofy.home.presentation.HomeScreen

@Composable
fun NonofyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.Home.name) {
        composable(Destinations.Home.name) {
            HomeScreen()
        }

        composable(Destinations.InGame.name) {
            InGameScreen()
        }

        composable(Destinations.Creative.name) {
            CreativeScreen()
        }
    }
}