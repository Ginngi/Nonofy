package com.nonofy.creative.draw.domain

import com.nonofy.creative.draw.presentation.CreativeScreenState
import com.nonofy.utils.Reducer
import javax.inject.Inject

class CreativeReducer @Inject constructor() : Reducer<CreativeEffect, CreativeScreenState> {
    override fun reduce(
        effect: CreativeEffect,
        oldModel: CreativeScreenState
    ): CreativeScreenState = when (effect) {
        is CreativeEffect.UpdatePixelAtPosition -> oldModel.copy(
            gridState = oldModel.gridState.copy(
                pixels = oldModel.gridState.pixels.toMutableList().apply {
                    this[effect.position] = effect.newPixelState
                }
            )
        )
    }
}