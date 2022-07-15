package com.nonofy.game.impl.domain.feature.performers

import com.nonofy.game.impl.domain.feature.InGameEffect
import com.nonofy.game.impl.domain.models.Grid
import com.nonofy.game.impl.domain.models.Header
import com.nonofy.game.impl.domain.models.Nonogram
import com.nonofy.game.impl.domain.models.Pixel
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

                val updatedPixelsBoard = updatePixelInBoardWithState(
                    row = row,
                    column = column,
                    board = params.nonogram.grid.pixels,
                    newPixel = newPixel
                )

                val updatedVerticalHeaders = updateVerticalHeaders(
                    updatedPixelsBoard,
                    params.nonogram.verticalHeaders,
                    column
                )

                val updatedHorizontalHeaders = updateHorizontalHeaders(
                    updatedPixelsBoard,
                    params.nonogram.horizontalHeaders,
                    row
                )

                val updatedGrid = updateGrid(
                    board = updatedPixelsBoard,
                    horizontalHeader = updatedHorizontalHeaders[row],
                    verticalHeader = updatedVerticalHeaders[column],
                    column = column,
                    row = row,
                    oldGrid = params.nonogram.grid,
                    numFilledPixels = numFilledPixels
                )

                val nonogram = params.nonogram.copy(
                    numErrors = numErrors,
                    grid = updatedGrid,
                    verticalHeaders = updatedVerticalHeaders,
                    horizontalHeaders = updatedHorizontalHeaders
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

    private fun updateGrid(
        board: List<List<Pixel>>,
        horizontalHeader: Header,
        verticalHeader: Header,
        column: Int,
        row: Int,
        oldGrid: Grid,
        numFilledPixels: Int
    ): Grid {
        val newBoard: MutableList<MutableList<Pixel>> =
            board.map { it.toMutableList() }.toMutableList()

        if (verticalHeader.isCompleted) {
            newBoard.forEach {
                if (it[column] == Pixel.EMPTY) {
                    it[column] = Pixel.ERROR
                }
            }
        }

        if (horizontalHeader.isCompleted) {
            newBoard[row].forEachIndexed { index, pixel ->
                if (pixel == Pixel.EMPTY) {
                    newBoard[row][index] = Pixel.ERROR
                }
            }
        }

        return oldGrid.copy(
            pixels = newBoard,
            numFilledPixels = numFilledPixels
        )
    }

    data class Params(val nonogram: Nonogram, val indexPixelClicked: Int)
}

const val NUM_LIFES = 3