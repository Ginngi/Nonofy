package com.nonofy.game.data

import com.nonofy.game.data.datasource.GameCacheDataSource
import com.nonofy.game.data.datasource.NonogramDataSource
import com.nonofy.game.data.mapper.NonogramMapper
import com.nonofy.game.domain.GameRepository
import com.nonofy.game.domain.models.Difficulty
import com.nonofy.game.domain.models.Nonogram
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val cacheDataSource: GameCacheDataSource,
    private val nonogramDataSource: NonogramDataSource,
    private val nonogramMapper: NonogramMapper
) : GameRepository {

    override fun loadGame(): Flow<Nonogram> = nonogramDataSource.test(Difficulty.MEDIUM)
        /*cacheDataSource.hasGameSaved().flatMapLatest { hasGameSaved ->
            if (hasGameSaved) {
                cacheDataSource.loadGame().map { nonogramMapper.map(it) }
            } else {
                nonogramDataSource.test()
            }
        }
         */

    override suspend fun saveGame(nonogram: Nonogram) {
        cacheDataSource.saveGame(nonogramMapper.map(nonogram))
    }
}