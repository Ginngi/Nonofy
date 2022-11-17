package com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.headers

data class LineState(
    val numberPixels: Int,
    val isCompleted: Boolean
) {
    companion object {
        fun empty(numberPixels: Int = 1, isCompleted: Boolean = false) = LineState(
            numberPixels = numberPixels,
            isCompleted = isCompleted
        )
    }
}