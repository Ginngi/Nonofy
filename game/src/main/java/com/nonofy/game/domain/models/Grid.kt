package com.nonofy.game.domain.models


data class Grid(
    val pixels: List<Pixel>,
    val numFilledPixels: Int,
    val size: Int
) {
    companion object {
        fun empty(difficulty: Difficulty = Difficulty.MEDIUM) = Grid(
            pixels = MutableList(difficulty.size * difficulty.size) { Pixel.EMPTY },
            numFilledPixels = 0,
            size = difficulty.size,
        )
    }
}