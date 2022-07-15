package com.nonofy.game.impl.presentation.components.ingameboard

import com.nonofy.game.impl.domain.models.Difficulty
import com.nonofy.game.impl.presentation.components.headers.HeaderState
import com.nonofy.ui.components.grid.GridState

data class InGameBoardState constructor(
    val horizontalHeader: List<HeaderState>,
    val verticalHeader: List<HeaderState>,
    val gridState: GridState,
    val difficulty: Difficulty
) {
    companion object {
        fun empty(difficulty: Difficulty = Difficulty.MEDIUM) = InGameBoardState(
            verticalHeader = MutableList(difficulty.size) { HeaderState.empty() },
            horizontalHeader = MutableList(difficulty.size) { HeaderState.empty() },
            difficulty = difficulty,
            gridState = GridState.empty(difficulty.size)
        )
    }
}