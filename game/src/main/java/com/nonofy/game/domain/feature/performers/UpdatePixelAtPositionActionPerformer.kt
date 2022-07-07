package com.nonofy.game.domain.feature.performers

import com.nonofy.game.domain.feature.InGameEffect
import com.nonofy.game.domain.models.Nonogram
import com.nonofy.game.domain.models.Pixel
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdatePixelAtPositionActionPerformer @Inject constructor(
) : Performer<UpdatePixelAtPositionActionPerformer.Params, InGameEffect>() {

    override fun createObservable(params: Params): Flow<InGameEffect> = flow {
        val pixels: List<Pixel> = params.nonogram.grid.pixels

        when (pixels[params.indexPixelClicked]) {
            Pixel.EMPTY -> {
                val newPixel =
                    getNextPixelFromCurrent(params.nonogram.solution.pixels[params.indexPixelClicked])

                val numErrors =
                    if (newPixel == Pixel.ERROR) {
                        params.nonogram.numErrors + 1
                    } else {
                        params.nonogram.numErrors
                    }

                val numFilledPixels = if (newPixel == Pixel.FILLED) {
                    params.nonogram.grid.numFilledPixels + 1
                } else {
                    params.nonogram.grid.numFilledPixels
                }

                emit(
                    when {
                        numErrors >= NUM_LIFES -> InGameEffect.GameOver
                        numFilledPixels == params.nonogram.solution.numFilledPixels -> InGameEffect.CompletedSuccessfully
                        else -> InGameEffect.GameLoaded(
                            nonogram = params.nonogram.copy(
                                numErrors = numErrors,
                                grid = params.nonogram.grid.copy(
                                    pixels = updatePixelInBoardWithState(
                                        index = params.indexPixelClicked,
                                        board = params.nonogram.grid.pixels,
                                        newPixel = newPixel
                                    ).toMutableList(),
                                    numFilledPixels = params.nonogram.grid.numFilledPixels + 1
                                ),
                            )
                        )
                    }
                )
            }
            else -> emit(InGameEffect.GameLoaded(params.nonogram))
        }
    }

    private fun getNextPixelFromCurrent(correctState: Pixel) =
        when (correctState) {
            Pixel.FILLED -> Pixel.FILLED
            else -> Pixel.ERROR
        }

    private fun updatePixelInBoardWithState(
        index: Int,
        board: List<Pixel>,
        newPixel: Pixel
    ): List<Pixel> {
        val newBoard = board.toMutableList()
        newBoard[index] = newPixel

        return newBoard
    }

    data class Params(val nonogram: Nonogram, val indexPixelClicked: Int)
}

const val NUM_LIFES = 3