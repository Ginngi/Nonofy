package com.nonofy.game.impl.presentation.mappers

import com.nonofy.game.impl.domain.models.Pixel
import com.nonofy.ui.components.pixel.PixelState
import javax.inject.Inject

class PixelStateMapper @Inject constructor() {
    fun map(model: Pixel): PixelState = when (model) {
        Pixel.FILLED -> PixelState.Filled
        Pixel.ERROR -> PixelState.Failed
        Pixel.EMPTY -> PixelState.Empty
    }
}