package com.nonofy.game.domain.feature.performers

import com.nonofy.game.domain.feature.InGameEffect
import com.nonofy.game.domain.models.Header
import com.nonofy.game.domain.models.Nonogram
import com.nonofy.game.domain.models.Pixel
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdatePixelAtPositionActionPerformer @Inject constructor(
) : Performer<UpdatePixelAtPositionActionPerformer.Params, InGameEffect>() {

    override fun createObservable(params: Params): Flow<InGameEffect> = flow {
        val grid: List<List<Pixel>> = params.nonogram.grid.pixels
        val row: Int = params.indexPixelClicked / params.nonogram.difficulty.size
        val column: Int = params.indexPixelClicked % params.nonogram.difficulty.size

        when (grid[row][column]) {
            Pixel.EMPTY -> {
                val newPixel =
                    getNextPixelFromCurrent(params.nonogram.solution.pixels[row][column])

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

                val updatedGrid = params.nonogram.grid.copy(
                    pixels = updatePixelInBoardWithState(
                        row = row,
                        column = column,
                        board = params.nonogram.grid.pixels,
                        newPixel = newPixel
                    ),
                    numFilledPixels = numFilledPixels
                )

                val nonogram = params.nonogram.copy(
                    numErrors = numErrors,
                    grid = updatedGrid,
                    verticalHeaders = updateVerticalHeaders(
                        updatedGrid.pixels,
                        params.nonogram.verticalHeaders,
                        column
                    ),
                    horizontalHeaders = updateHorizontalHeaders(
                        updatedGrid.pixels,
                        params.nonogram.horizontalHeaders,
                        row
                    )
                )

                emit(
                    if (numErrors >= NUM_LIFES) {
                        InGameEffect.GameOver(nonogram)
                    } else if (numFilledPixels == params.nonogram.solution.numFilledPixels) {
                        InGameEffect.CompletedSuccessfully(nonogram)
                    } else {
                        InGameEffect.GameLoaded(nonogram)
                    }
                )
            }
            else -> emit(InGameEffect.NoChanges)
        }
    }

    private fun getNextPixelFromCurrent(correctState: Pixel) =
        when (correctState) {
            Pixel.FILLED -> Pixel.FILLED
            else -> Pixel.ERROR
        }

    private fun updatePixelInBoardWithState(
        row: Int,
        column: Int,
        board: List<List<Pixel>>,
        newPixel: Pixel
    ): List<List<Pixel>> = board.toMutableList().apply {
        val pixelsRow = this[row].toMutableList()
        pixelsRow[column] = newPixel
        this[row] = pixelsRow
    }

    private fun updateHorizontalHeaders(
        grid: List<List<Pixel>>,
        headerList: List<Header>,
        row: Int
    ): List<Header> {
        val numFilledPixelsInRow = grid[row].count { it == Pixel.FILLED }
        val isCompleted = numFilledPixelsInRow >= headerList[row].filledPixels

        val newHeader = headerList[row].copy(
            isCompleted = isCompleted
        )

        return headerList.toMutableList().apply {
            this[row] = newHeader
        }
    }

    private fun updateVerticalHeaders(
        grid: List<List<Pixel>>,
        headerList: List<Header>,
        column: Int
    ): List<Header> {
        var numFilledPixelsInRow = 0

        grid.forEach {
            if (it[column] == Pixel.FILLED) {
                numFilledPixelsInRow++
            }
        }

        val isCompleted = numFilledPixelsInRow >= headerList[column].filledPixels
        val newHeader = headerList[column].copy(
            isCompleted = isCompleted
        )

        return headerList.toMutableList().apply {
            this[column] = newHeader
        }
    }

    data class Params(val nonogram: Nonogram, val indexPixelClicked: Int)
}

const val NUM_LIFES = 3