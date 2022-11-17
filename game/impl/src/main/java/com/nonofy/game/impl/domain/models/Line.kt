package com.nonofy.game.impl.com.nonofy.game.impl.domain.models

data class Line(
    val numberPixels: Int,
    val isCompleted: Boolean
) {
    companion object {
        fun empty(numberPixels: Int = 1, isCompleted: Boolean = false) = Line(
            numberPixels = numberPixels,
            isCompleted = isCompleted
        )
    }
}