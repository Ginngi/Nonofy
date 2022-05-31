package com.nonofy.ui.components.grid

import com.nonofy.ui.components.pixel.PixelState

data class GridState(
    val pixels: List<List<PixelState>>,
    val size: Int
) {
    companion object {
        fun empty(size: Int = 10) = GridState(
            pixels = MutableList(size) { MutableList(size) { PixelState.Empty } },
            size = size
        )
    }
}