package com.nonofy.game.presentation.components.ingameboard

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nonofy.game.presentation.InGameInteraction
import com.nonofy.game.presentation.components.board.Board
import com.nonofy.game.presentation.components.headers.HorizontalBoardHeader
import com.nonofy.game.presentation.components.headers.VerticalBoardHeader

@Composable
fun InGameBoard(
    inGameBoardState: InGameBoardState,
    boardIntentListener: (InGameInteraction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(0.85f)
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.Bottom
        ) {
            for (header in inGameBoardState.verticalHeader) {
                VerticalBoardHeader(
                    header = header,
                    modifier = Modifier
                        .weight(1f / inGameBoardState.size)
                        .fillMaxHeight()
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column(
                Modifier
                    .weight(0.15f)
                    .fillMaxHeight()
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.End
            ) {
                for (header in inGameBoardState.verticalHeader) {
                    HorizontalBoardHeader(
                        header = header,
                        modifier = Modifier
                            .weight(1f / inGameBoardState.size)
                            .fillMaxWidth()
                    )
                }
            }

            Board(
                state = inGameBoardState.boardState,
                intentsListener = { boardIntentListener(it) },
                modifier = Modifier.weight(0.85f)
            )
        }
    }
}