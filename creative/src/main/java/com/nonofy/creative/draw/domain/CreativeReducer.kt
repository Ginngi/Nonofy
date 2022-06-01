package com.nonofy.creative.draw.domain

import com.nonofy.creative.draw.presentation.CreativeScreenState
import com.nonofy.utils.Reducer
import javax.inject.Inject

class CreativeReducer @Inject constructor() : Reducer<CreativeEffect, CreativeScreenState> {
    override fun reduce(
        effect: CreativeEffect,
        oldModel: CreativeScreenState
    ): CreativeScreenState {
        TODO("Not yet implemented")
    }
}