package com.nonofy.game.presentation.components.ingameboard

import com.nonofy.game.domain.models.Difficulty
import com.nonofy.game.presentation.components.board.GridState

data class InGameBoardState constructor(
    val horizontalHeader: List<String>,
    val verticalHeader: List<String>,
    val gridState: GridState,
    val difficulty: Difficulty
) {
    companion object {
        fun empty(difficulty: Difficulty = Difficulty.MEDIUM) = InGameBoardState(
            verticalHeader = MutableList(difficulty.size) { "1,1,1,1" },
            horizontalHeader = MutableList(difficulty.size) { "1,1,1,1" },
            difficulty = difficulty,
            gridState = GridState.empty(difficulty)
        )
    }
}