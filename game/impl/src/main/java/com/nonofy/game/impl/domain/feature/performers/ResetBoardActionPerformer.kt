package com.nonofy.game.impl.domain.feature.performers

import com.nonofy.game.impl.domain.feature.InGameEffect
import com.nonofy.game.impl.domain.models.Difficulty
import com.nonofy.game.impl.domain.models.Grid
import com.nonofy.game.impl.domain.models.Header
import com.nonofy.game.impl.domain.models.Nonogram
import com.nonofy.game.impl.domain.models.Pixel
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResetBoardActionPerformer @Inject constructor(
) : Performer<ResetBoardActionPerformer.Params, InGameEffect.GameLoaded>() {

    override fun createObservable(params: Params): Flow<InGameEffect.GameLoaded> = flow {
        val verticalHeaders = params.nonogram.verticalHeaders.map { header ->
            val lines = header.lines.map { line ->
                if (line.numberPixels > 0) {
                    line.copy(isCompleted = false)
                } else line
            }

            if (header.filledPixels > 0) {
                header.copy(
                    lines = lines,
                    isCompleted = false
                )
            } else header
        }

        val horizontalHeaders = params.nonogram.horizontalHeaders.map { header ->
            val lines = header.lines.map { line ->
                if (line.numberPixels > 0) {
                    line.copy(isCompleted = false)
                } else line
            }

            if (header.filledPixels > 0) {
                header.copy(
                    lines = lines,
                    isCompleted = false
                )
            } else header
        }

        val grid = createGrid(verticalHeaders, horizontalHeaders, params.nonogram.difficulty)

        emit(
            InGameEffect.GameLoaded(
                nonogram = params.nonogram.copy(
                    numErrors = 0,
                    grid = grid,
                    verticalHeaders = verticalHeaders,
                    horizontalHeaders = horizontalHeaders
                )
            )
        )
    }

    private fun createGrid(
        verticalHeaders: List<Header>,
        horizontalHeaders: List<Header>,
        difficulty: Difficulty
    ): Grid {
        val newBoard: MutableList<MutableList<Pixel>> =
            MutableList(difficulty.size) { MutableList(difficulty.size) { Pixel.EMPTY } }

        verticalHeaders.forEachIndexed { index, header ->
            if (header.isCompleted) {
                newBoard.forEach {
                    if (it[index] == Pixel.EMPTY) {
                        it[index] = Pixel.ERROR
                    }
                }
            }
        }

        horizontalHeaders.forEachIndexed { index, header ->
            if (header.isCompleted) {
                newBoard[index].forEachIndexed { i, pixel ->
                    if (pixel == Pixel.EMPTY) {
                        newBoard[index][i] = Pixel.ERROR
                    }
                }
            }
        }

        return Grid(
            pixels = newBoard,
            numFilledPixels = 0,
            size = difficulty.size
        )
    }

    data class Params(val nonogram: Nonogram)
}