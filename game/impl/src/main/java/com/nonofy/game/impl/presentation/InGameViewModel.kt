package com.nonofy.game.impl.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonofy.game.api.KEY_GAME_ID_PARAM
import com.nonofy.game.impl.domain.feature.InGameEvent
import com.nonofy.game.impl.domain.feature.InGameLogic
import com.nonofy.game.impl.presentation.mappers.InGameStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InGameViewModel @Inject constructor(
    private val inGameLogic: InGameLogic,
    private val stateMapper: InGameStateMapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state: StateFlow<InGameState> = inGameLogic.model.map {
        stateMapper.map(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        InGameState.loading()
    )

    init {
        val gameId = savedStateHandle.get<String>(KEY_GAME_ID_PARAM).orEmpty()
        viewModelScope.launch { inGameLogic.init(gameId) }
    }

    fun event(event: InGameEvent) {
        viewModelScope.launch {
            inGameLogic.onEvent(event)
        }
    }
}