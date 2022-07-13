package com.nonofy.game.domain.feature.performers


import com.nonofy.game.domain.feature.InGameEffect
import com.nonofy.game.domain.models.Grid
import com.nonofy.game.domain.models.Nonogram
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResetBoardActionPerformer @Inject constructor(
) : Performer<ResetBoardActionPerformer.Params, InGameEffect.GameLoaded>() {

    override fun createObservable(params: Params): Flow<InGameEffect.GameLoaded> = flow {
        emit(
            InGameEffect.GameLoaded(
                nonogram = params.nonogram.copy(
                    numErrors = 0,
                    grid = params.nonogram.grid.copy(
                        numFilledPixels = 0,
                        pixels = Grid.empty(params.nonogram.difficulty).pixels
                    ),
                    verticalHeaders = params.nonogram.verticalHeaders.map {
                        if (it.filledPixels > 0) {
                            it.copy(isCompleted = false)
                        } else it
                    },
                    horizontalHeaders = params.nonogram.horizontalHeaders.map {
                        if (it.filledPixels > 0) {
                            it.copy(isCompleted = false)
                        } else it
                    }
                )
            )
        )
    }

    data class Params(val nonogram: Nonogram)
}