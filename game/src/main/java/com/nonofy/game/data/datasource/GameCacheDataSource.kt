package com.nonofy.game.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.nonofy.game.data.proto.GameEntity
import com.nonofy.game.data.serializer.NonogramEntitySerializer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameCacheDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.gameDataStore: DataStore<GameEntity.NonogramEntity> by dataStore(
        fileName = "game",
        serializer = NonogramEntitySerializer
    )

    fun loadGame(): Flow<GameEntity.NonogramEntity> =
        context.gameDataStore.data

    fun hasGameSaved(): Flow<Boolean> = context.gameDataStore.data.map {
        it.currentGrid.pixelsList.isNotEmpty()
    }

    suspend fun saveGame(game: GameEntity.NonogramEntity) {
        context.gameDataStore.updateData {
            it.toBuilder()
                .setTitle(game.title)
                .setNumErrors(game.numErrors)
                .setSolution(game.solution)
                .setCurrentGrid(game.currentGrid)
                .build()
        }
    }
}