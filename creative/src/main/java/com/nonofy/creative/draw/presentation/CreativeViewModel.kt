package com.nonofy.creative.draw.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonofy.creative.CREATIVE_DRAW_GRID_SIZE_ARG
import com.nonofy.creative.draw.domain.CreativeEvent
import com.nonofy.creative.draw.domain.CreativeLogic
import com.nonofy.ui.components.grid.GridState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreativeViewModel @Inject constructor(
    creativeLogicFactory: CreativeLogic.Factory,
    stateHandle: SavedStateHandle
) : ViewModel() {
    lateinit var logic: CreativeLogic

    val state: StateFlow<CreativeScreenState> by lazy {
        logic.model
    }

    init {
        val gridSize: Int = stateHandle.get(CREATIVE_DRAW_GRID_SIZE_ARG) ?: 10
        logic = creativeLogicFactory.create(gridSize)
    }

    fun event(event: CreativeEvent) {
        viewModelScope.launch {
            logic.onEvent(event)
        }
    }
}

data class CreativeScreenState(
    val gridState: GridState
)