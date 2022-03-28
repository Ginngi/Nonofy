package com.nonofy.game.presentation.components.ingameboard

import com.nonofy.game.presentation.components.board.GridState

data class InGameBoardState constructor(
    val horizontalHeader: List<String>,
    val verticalHeader: List<String>,
    val gridState: GridState,
) {
    companion object {
        fun empty(size: Int) = InGameBoardState(
            verticalHeader = MutableList(size) { "11" },
            horizontalHeader = MutableList(size) { "11" },
            gridState = GridState.empty(size)
        )
    }
}