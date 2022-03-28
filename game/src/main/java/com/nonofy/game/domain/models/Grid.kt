package com.nonofy.game.domain.models


data class Grid(
    val pixels: List<Pixel>,
    val numFilledPixels: Int,
    val size: Int
) {
    companion object {
        fun empty(size: Int = 10) = Grid(
            pixels = MutableList(size * size) { Pixel.EMPTY },
            numFilledPixels = 0,
            size = size
        )
    }
}