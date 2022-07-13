package com.nonofy.game.presentation.mappers

import com.nonofy.game.domain.feature.InGameModel
import com.nonofy.game.presentation.InGameState
import com.nonofy.game.presentation.components.ingameboard.InGameBoardState
import com.nonofy.ui.components.grid.GridState
import javax.inject.Inject

class InGameStateMapper @Inject constructor(
    private val pixelMapper: PixelStateMapper,
    private val headerStateMapper: HeaderStateMapper
) {
    fun map(inGameModel: InGameModel): InGameState = InGameState(
        title = inGameModel.nonogram.title,
        numErrors = inGameModel.nonogram.numErrors,
        isLoading = inGameModel.isLoading,
        isGameOver = inGameModel.isGameOver,
        isCompletedSuccessfully = inGameModel.isCompletedSuccessfully,
        inGameBoardState = InGameBoardState(
            horizontalHeader = inGameModel.nonogram.horizontalHeaders.map { headerStateMapper.map(it) },
            verticalHeader = inGameModel.nonogram.verticalHeaders.map { headerStateMapper.map(it) },
            gridState = GridState(
                pixels = inGameModel.nonogram.grid.pixels.flatten()
                    .map { pixelMapper.map(it) },
                size = inGameModel.nonogram.grid.size,
            ),
            difficulty = inGameModel.nonogram.difficulty
        )
    )
}