package com.nonofy.game.presentation.components.board

import com.nonofy.ui.components.pixelbox.PixelState

data class BoardState constructor(
    val pixels: List<List<PixelState>>,
    val numPixelsFilled: Int = 0,
)