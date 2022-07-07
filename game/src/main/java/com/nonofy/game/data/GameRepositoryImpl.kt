package com.nonofy.game.data

import com.nonofy.game.data.datasource.GameCacheDataSource
import com.nonofy.game.data.datasource.NonogramDataSource
import com.nonofy.game.data.mapper.NonogramMapper
import com.nonofy.game.data.serializer.NonogramEntitySerializer
import com.nonofy.game.domain.GameRepository
import com.nonofy.game.domain.models.Nonogram
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val cacheDataSource: GameCacheDataSource,
    private val nonogramDataSource: NonogramDataSource,
    private val nonogramMapper: NonogramMapper
) : GameRepository {

    override fun loadGame(): Flow<Nonogram> =
        cacheDataSource.loadGame()
            .map {
                return@map if (it == NonogramEntitySerializer.defaultValue) {
                    nonogramMapper.map(nonogramDataSource.easyTest())
                } else {
                    it
                }
            }
            .map {
                nonogramMapper.map(it)
            }

    override suspend fun saveGame(nonogram: Nonogram) {
        cacheDataSource.saveGame(nonogramMapper.map(nonogram))
    }
}