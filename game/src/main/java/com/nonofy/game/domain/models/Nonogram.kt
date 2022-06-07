package com.nonofy.game.domain.models

data class Nonogram(
    val title: String,
    val numErrors: Int,
    val grid: Grid,
    val solution: Grid,
    val verticalHeaders: List<Header>,
    val horizontalHeaders: List<Header>,
    val difficulty: Difficulty
) {
    companion object {
        fun empty(
            title: String = "",
            numErrors: Int = 0,
            grid: Grid = Grid.empty(),
            solution: Grid = Grid.empty(),
            verticalHeaders: List<Header> = MutableList(Difficulty.MEDIUM.size) { Header.empty() },
            horizontalHeaders: List<Header> = MutableList(Difficulty.MEDIUM.size) { Header.empty() },
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