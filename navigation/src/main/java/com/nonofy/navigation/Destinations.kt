package com.nonofy.navigation

import com.nonofy.game.api.KEY_GAME_ID_PARAM

sealed class Destinations(val uri: String) {
    object Home : Destinations("home")
    data class InGame(private val gameId: String) : Destinations("game/$gameId")
    object Creative : Destinations("creative")
}

const val GAME_SCREEN_ROUTE = "game/{$KEY_GAME_ID_PARAM}"