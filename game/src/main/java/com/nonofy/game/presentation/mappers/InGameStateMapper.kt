package com.nonofy.game.presentation.mappers

import com.nonofy.game.domain.feature.InGameModel
import com.nonofy.game.presentation.InGameState
import com.nonofy.ui.components.grid.GridState
import com.nonofy.game.presentation.components.ingameboard.InGameBoardState
import javax.inject.Inject

class InGameStateMapper @Inject constructor(
    private val pixelMapper: PixelStateMapper
) {
    fun map(inGameModel: InGameModel): InGameState = InGameState(
        title = inGameModel.nonogram.title,
        numErrors = inGameModel.nonogram.numErrors,
        isLoading = inGameModel.isLoading,
        isGameOver = inGameModel.isGameOver,
        inGameBoardState = InGameBoardState(
            horizontalHeader = inGameModel.nonogram.horizontalHeaders,
            verticalHeader = inGameModel.nonogram.verticalHeaders,
            gridState = GridState(
                pixels = inGameModel.nonogram.grid.pixels
                    .map { pixelMapper.map(it) }
                    .chunked(inGameModel.nonogram.grid.size),
                size = inGameModel.nonogram.grid.size,
            ),
            difficulty = inGameModel.nonogram.difficulty
        )
    )
}