package com.nonofy.game.domain

import com.nonofy.game.domain.models.Nonogram
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun loadGameById(): Flow<Nonogram>
    fun loadSavedGame(): Flow<Nonogram>
    suspend fun saveGame(nonogram: Nonogram)
}