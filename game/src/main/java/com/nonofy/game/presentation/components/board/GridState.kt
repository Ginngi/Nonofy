package com.nonofy.game.presentation.components.board

import com.nonofy.ui.components.pixelbox.PixelState

data class GridState(
    val pixels: List<List<PixelState>>,
    val size: Int
) {
    companion object {
        fun empty(size: Int) = GridState(
            pixels = MutableList(size) { MutableList(size) { PixelState.Empty } },
            size = size
        )
    }
}