package com.nonofy.game.impl.domain.models

import com.nonofy.game.impl.com.nonofy.game.impl.domain.models.Line

data class Header constructor(
    val lines: List<Line>,
    val filledPixels: Int,
    val isCompleted: Boolean
) {
    companion object {
        fun empty() = Header(
            lines = listOf(Line.empty(2), Line.empty(1)),
            filledPixels = 0,
            isCompleted = false
        )
    }
}