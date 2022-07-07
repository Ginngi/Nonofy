package com.nonofy.game.data

import com.nonofy.game.data.datasource.GameCacheDataSource
import com.nonofy.game.data.datasource.NonogramDataSource
import com.nonofy.game.data.mapper.NonogramMapper
import com.nonofy.game.domain.GameRepository
import com.nonofy.game.domain.models.Nonogram
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val cacheDataSource: GameCacheDataSource,
    private val nonogramDataSource: NonogramDataSource,
    private val nonogramMapper: NonogramMapper
) : GameRepository {

    override fun loadGameById(): Flow<Nonogram> = flow {
        emit(nonogramDataSource.kostasGame())
    }

    override fun loadSavedGame(): Flow<Nonogram> =
        cacheDataSource.loadGame()
            .map { nonogramMapper.map(it) }

    override suspend fun saveGame(nonogram: Nonogram) {
        cacheDataSource.saveGame(nonogramMapper.map(nonogram))
    }
}