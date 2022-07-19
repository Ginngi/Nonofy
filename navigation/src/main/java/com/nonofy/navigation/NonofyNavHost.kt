package com.nonofy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nonofy.creative.creativeGraph
import com.nonofy.game.api.KEY_GAME_ID_PARAM
import com.nonofy.game.impl.presentation.InGameScreen
import com.nonofy.home.presentation.HomeScreen

@Composable
fun NonofyNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.Home.uri) {
        composable(Destinations.Home.uri) {
            HomeScreen()
        }

        composable(
            GAME_SCREEN_ROUTE,
            arguments = listOf(navArgument(KEY_GAME_ID_PARAM) { type = NavType.StringType })
        ) {
            val gameId = it.arguments?.getString(KEY_GAME_ID_PARAM, "") ?: ""
            InGameScreen(gameId)
        }

        creativeGraph(navController, Destinations.Creative.uri)
    }
}