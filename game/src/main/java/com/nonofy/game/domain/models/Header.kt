package com.nonofy.game.domain.models

data class Header constructor(
    val value: String,
    val isCompleted: Boolean
) {
    companion object {
        fun empty() = Header(
            "1,1,1",
            false
        )
    }
}