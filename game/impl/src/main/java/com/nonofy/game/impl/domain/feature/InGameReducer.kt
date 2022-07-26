package com.nonofy.game.impl.domain.feature

import com.nonofy.utils.Reducer
import javax.inject.Inject

class InGameReducer @Inject constructor() : Reducer<InGameEffect, InGameModel> {

    override fun reduce(effect: InGameEffect, oldModel: InGameModel): InGameModel = when (effect) {
        is InGameEffect.GameLoaded -> oldModel.copy(
            isLoading = false,
            isGameOver = false,
            isCompletedSuccessfully = false,
            nonogram = effect.nonogram,
        )
        is InGameEffect.GameOver -> oldModel.copy(nonogram = effect.nonogram, isGameOver = true)
        is InGameEffect.NoChanges -> oldModel
        is InGameEffect.CompletedSuccessfully -> oldModel.copy(
            nonogram = effect.nonogram,
            isCompletedSuccessfully = true
        )
        is InGameEffect.ChangePixelMode -> oldModel.copy(
            isPixelEnabled = effect.pixelModeEnabled
        )
    }
}