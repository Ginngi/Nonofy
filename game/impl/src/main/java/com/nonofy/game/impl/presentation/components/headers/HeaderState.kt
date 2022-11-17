package com.nonofy.game.impl.presentation.components.headers

import com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.headers.LineState

data class HeaderState(
    val lines: List<LineState>,
    val filledPixels: Int,
    val isCompleted: Boolean
) {
    companion object {
        fun empty(
            isCompleted: Boolean = false,
            lines: List<LineState> = listOf(LineState.empty(2), LineState.empty(1))
        ) = HeaderState(
            lines = lines,
            filledPixels = 0,
            isCompleted = isCompleted
        )
    }
}