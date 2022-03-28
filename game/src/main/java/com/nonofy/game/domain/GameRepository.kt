package com.nonofy.game.domain

import com.nonofy.game.domain.models.Nonogram
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun loadGame(): Flow<Nonogram>

    suspend fun saveGame(nonogram: Nonogram)
}