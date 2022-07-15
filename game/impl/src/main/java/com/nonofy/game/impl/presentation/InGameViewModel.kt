package com.nonofy.game.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val stateMapper: InGameStateMapper
) : ViewModel() {

    val state: StateFlow<InGameState> = inGameLogic.model.map {
        stateMapper.map(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        InGameState.loading()
    )

    init {
        viewModelScope.launch { inGameLogic.init() }
    }

    fun event(event: InGameEvent) {
        viewModelScope.launch {
            inGameLogic.onEvent(event)
        }
    }
}