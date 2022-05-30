package com.nonofy.game.domain.models

data class Nonogram(
    val title: String,
    val numErrors: Int,
    val grid: Grid,
    val solution: Grid,
    val verticalHeaders: List<String>,
    val horizontalHeaders: List<String>,
    val difficulty: Difficulty
) {
    companion object {
        fun empty(
            title: String = "",
            numErrors: Int = 0,
            grid: Grid = Grid.empty(),
            solution: Grid = Grid.empty(),
            verticalHeaders: List<String> = MutableList(10) { "1,1,1" },
            horizontalHeaders: List<String> = MutableList(10) { "1,2,3" },
            difficulty: Difficulty = Difficulty.MEDIUM
        ) = Nonogram(
            title = title,
            numErrors = numErrors,
            grid = grid,
            solution = solution,
            verticalHeaders = verticalHeaders,
            horizontalHeaders = horizontalHeaders,
            difficulty = difficulty
        )
    }
}