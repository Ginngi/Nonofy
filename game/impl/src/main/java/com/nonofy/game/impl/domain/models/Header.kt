package com.nonofy.game.impl.domain.models

data class Header constructor(
    val value: String,
    val filledPixels: Int,
    val isCompleted: Boolean
) {
    companion object {
        fun empty() = Header(
            value = "1,1,1",
            filledPixels = 0,
            isCompleted = false
        )
    }
}