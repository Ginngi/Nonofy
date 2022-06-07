package com.nonofy.game.presentation.components.headers

data class HeaderState(
    val value: String,
    val isCompleted: Boolean
) {
    companion object {
        fun empty() = HeaderState(
            "1,1,1",
            false
        )
    }
}