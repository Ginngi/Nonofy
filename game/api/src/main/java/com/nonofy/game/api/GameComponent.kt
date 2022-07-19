package com.nonofy.game.api

import kotlinx.coroutines.flow.Flow

interface GameComponent {
    fun hasGameSaved(): Flow<Boolean>
}

const val KEY_GAME_ID_PARAM = "gameId"