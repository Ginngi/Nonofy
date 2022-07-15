package com.nonofy.game.impl.presentation.components.headers

data class HeaderState(
    val value: String,
    val isCompleted: Boolean
) {
    companion object {
        fun empty(isCompleted: Boolean = false) = HeaderState(
            "1,1,1",
            isCompleted
        )
    }
}