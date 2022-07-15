package com.nonofy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nonofy.creative.creativeGraph
import com.nonofy.game.impl.presentation.InGameScreen
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

        creativeGraph(navController, Destinations.Creative.name)
    }
}