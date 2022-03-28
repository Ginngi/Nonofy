package com.nonofy.game.domain.models

data class Nonogram(
    val title: String,
    val numErrors: Int,
    val grid: Grid,
    val solution: Grid,
    val verticalHeaders: List<String>,
    val horizontalHeaders: List<String>
) {
    companion object {
        fun empty(
            title: String = "",
            numErrors: Int = 0,
            grid: Grid = Grid.empty(),
            solution: Grid = Grid.empty(),
            verticalHeaders: List<String> = MutableList(10) { "111" },
            horizontalHeaders: List<String> = MutableList(10) { "123" }
        ) = Nonogram(
            title = title,
            numErrors = numErrors,
            grid = grid,
            solution = solution,
            verticalHeaders = verticalHeaders,
            horizontalHeaders = horizontalHeaders,
        )
    }
}