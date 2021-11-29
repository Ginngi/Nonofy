package com.nonofy.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nonofy.game.presentation.InGameScreen
import com.nonofy.home.HomeScreen

@Composable
fun NonofyNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.InGame.name) {
        composable(Destinations.Home.name) {
            HomeScreen(viewModel = hiltViewModel())
        }

        composable(Destinations.InGame.name) {
            InGameScreen(inGameViewModel = hiltViewModel())
        }
    }
}