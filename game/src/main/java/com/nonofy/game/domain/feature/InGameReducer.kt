package com.nonofy.game.domain.feature

import com.nonofy.utils.Reducer
import javax.inject.Inject

class InGameReducer @Inject constructor() : Reducer<InGameEffect, InGameModel> {

    override fun reduce(effect: InGameEffect, oldModel: InGameModel): InGameModel = when (effect) {
        is InGameEffect.GameLoaded -> InGameModel(
            isLoading = false, isGameOver = false, nonogram = effect.nonogram
        )

        is InGameEffect.GameOver -> oldModel.copy(isGameOver = true)
        is InGameEffect.NoChanges -> oldModel
    }
}