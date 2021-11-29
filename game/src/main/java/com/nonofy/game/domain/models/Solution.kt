package com.nonofy.game.domain.models

import com.nonofy.ui.components.pixelbox.PixelState

data class Solution(
    val pixels: List<List<PixelState>>,
    val numFilledPixels: Int
)