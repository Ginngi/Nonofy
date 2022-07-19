package com.nonofy.game.impl.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.nonofy.game.impl.data.proto.GameEntity
import com.nonofy.game.impl.data.serializer.NonogramEntitySerializer
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

    private val Context.gameStateDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "game_state"
    )

    fun loadGame(): Flow<GameEntity.NonogramEntity> =
        context.gameDataStore.data

    suspend fun saveGame(game: GameEntity.NonogramEntity) {
        context.gameDataStore.updateData {
            val builder = it.toBuilder()

            builder
                .setTitle(game.title)
                .setNumErrors(game.numErrors)
                .setSolution(game.solution)
                .setCurrentGrid(game.currentGrid)
                .setDifficulty(game.difficulty)
                .clearVerticalHeaders()
                .addAllVerticalHeaders(game.verticalHeadersList)
                .clearHorizontalHeaders()
                .addAllHorizontalHeaders(game.horizontalHeadersList)
                .build()
        }

        context.gameStateDataStore.edit { settings ->
            settings[KEY_GAME_STARTED] = true
        }
    }

    val hasGameStarted: Flow<Boolean> = context.gameStateDataStore.data
        .map { preferences -> preferences[KEY_GAME_STARTED] ?: false }

    private val KEY_GAME_STARTED = booleanPreferencesKey("game_started")
}