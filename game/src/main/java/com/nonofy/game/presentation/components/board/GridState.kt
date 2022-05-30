package com.nonofy.game.presentation.components.board

import com.nonofy.game.domain.models.Difficulty
import com.nonofy.ui.components.pixelbox.PixelState

data class GridState(
    val pixels: List<List<PixelState>>,
    val size: Int
) {
    companion object {
        fun empty(difficulty: Difficulty = Difficulty.MEDIUM) = GridState(
            pixels = MutableList(difficulty.size) { MutableList(difficulty.size) { PixelState.Empty } },
            size = difficulty.size
        )
    }
}