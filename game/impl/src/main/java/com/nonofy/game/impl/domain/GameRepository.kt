package com.nonofy.game.impl.domain

import com.nonofy.game.impl.domain.models.Nonogram
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    fun loadGameById(): Flow<Nonogram>
    fun loadSavedGame(): Flow<Nonogram>
    fun hasSavedGame(): Flow<Boolean>
    suspend fun saveGame(nonogram: Nonogram)
}