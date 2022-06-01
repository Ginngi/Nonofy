package com.nonofy.creative

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.nonofy.creative.draw.presentation.CreativeScreen
import com.nonofy.creative.sizeselection.CreativeSizeSelectionScreen

fun NavGraphBuilder.creativeGraph(navController: NavController, route: String) {
    navigation(startDestination = CreativeDestinations.DifficultySelection.name, route = route) {
        composable(CreativeDestinations.DifficultySelection.name) {
            CreativeSizeSelectionScreen(navController)
        }

        composable(
            route = CREATIVE_DRAW_ROUTE,
            arguments = listOf(navArgument(CREATIVE_DRAW_GRID_SIZE_ARG) { type = NavType.IntType })
        ) {
            CreativeScreen()
        }
    }
}

fun buildCreativeDrawRoute(gridSize: Int): String =
    "${CreativeDestinations.Draw.name}/$gridSize"

private val CREATIVE_DRAW_ROUTE: String
    get() = "${CreativeDestinations.Draw.name}/{$CREATIVE_DRAW_GRID_SIZE_ARG}"

const val CREATIVE_DRAW_GRID_SIZE_ARG = "gridSizeArg"

enum class CreativeDestinations {
    DifficultySelection, Draw
}