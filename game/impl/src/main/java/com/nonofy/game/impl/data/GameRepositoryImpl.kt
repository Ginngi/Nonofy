package com.nonofy.game.impl.data

import com.nonofy.game.impl.data.datasource.GameCacheDataSource
import com.nonofy.game.impl.data.datasource.NonogramDataSource
import com.nonofy.game.impl.data.mapper.NonogramMapper
import com.nonofy.game.impl.domain.GameRepository
import com.nonofy.game.impl.domain.models.Nonogram
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
        emit(nonogramDataSource.easyTest())
    }

    override fun loadSavedGame(): Flow<Nonogram> =
        cacheDataSource.loadGame()
            .map { nonogramMapper.map(it) }

    override suspend fun saveGame(nonogram: Nonogram) {
        cacheDataSource.saveGame(nonogramMapper.map(nonogram))
    }
}