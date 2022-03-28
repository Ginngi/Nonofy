package com.nonofy.game.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.nonofy.game.data.proto.Game
import com.nonofy.game.data.serializer.NonogramEntitySerializer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameCacheDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.gameDataStore: DataStore<Game.NonogramEntity> by dataStore(
        fileName = "game",
        serializer = NonogramEntitySerializer
    )

    fun loadGame(): Flow<Game.NonogramEntity> =
        context.gameDataStore.data.onEach {
            val a = it.currentGrid
        }.catch {
            val a = 1
        }

    fun hasGameSaved(): Flow<Boolean> = context.gameDataStore.data.map {
        it.currentGrid.pixelsList.isNotEmpty()
    }

    suspend fun saveGame(game: Game.NonogramEntity) {
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