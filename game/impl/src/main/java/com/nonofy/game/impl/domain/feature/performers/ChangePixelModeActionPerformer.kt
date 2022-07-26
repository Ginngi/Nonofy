package com.nonofy.game.impl.com.nonofy.game.impl.domain.feature.performers

import com.nonofy.game.impl.domain.feature.InGameEffect
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangePixelModeActionPerformer @Inject constructor(
) : Performer<ChangePixelModeActionPerformer.Params, InGameEffect.ChangePixelMode>() {

    override fun createObservable(params: Params): Flow<InGameEffect.ChangePixelMode> = flow {
        emit(InGameEffect.ChangePixelMode(params.isEnabled))
    }

    data class Params(val isEnabled: Boolean)
}