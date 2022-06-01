package com.nonofy.creative.draw.domain

import com.nonofy.ui.components.pixel.PixelState

sealed class CreativeEvent {
    data class OnClickPixel(val position: Int) : CreativeEvent()
}

sealed class CreativeEffect {
    data class UpdatePixelAtPosition(
        val position: Int,
        val newPixelState: PixelState
    ) : CreativeEffect()
}