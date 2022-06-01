package com.nonofy.creative.draw.domain.performers

import com.nonofy.creative.draw.domain.CreativeEffect
import com.nonofy.ui.components.pixel.PixelState
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdatePixelAtPositionPerformer @Inject constructor(
) : Performer<UpdatePixelAtPositionPerformer.Params, CreativeEffect>() {

    override fun createObservable(params: Params): Flow<CreativeEffect> = flow {
        when (params.oldPixelState) {
            is PixelState.Empty -> emit(
                CreativeEffect.UpdatePixelAtPosition(
                    params.indexPixelClicked,
                    PixelState.Filled
                )
            )
            else -> emit(
                CreativeEffect.UpdatePixelAtPosition(
                    params.indexPixelClicked,
                    PixelState.Empty
                )
            )
        }
    }

    data class Params(val oldPixelState: PixelState, val indexPixelClicked: Int)
}