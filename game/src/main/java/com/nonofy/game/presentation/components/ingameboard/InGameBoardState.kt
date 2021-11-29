package com.nonofy.game.presentation.components.ingameboard

import com.nonofy.game.presentation.components.board.BoardState
import com.nonofy.ui.components.pixelbox.PixelState

data class InGameBoardState constructor(
    val horizontalHeader: List<String>,
    val verticalHeader: List<String>,
    val boardState: BoardState,
    val size: Int
)

val nonogramTestData = InGameBoardState(
    boardState = BoardState(
        pixels = listOf(
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            ),
            listOf(
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty,
                PixelState.Empty
            )
        ),
        numPixelsFilled = 0
    ),
    verticalHeader = listOf("11", "12", "13", "14", "14", "14", "14", "14", "1456", "14"),
    horizontalHeader = listOf("11", "21", "31", "41", "41", "41325", "41", "41", "41", "41"),
    size = 10
)