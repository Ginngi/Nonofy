package com.nonofy.game.presentation.components.ingameboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.domain.feature.InGameEvent
import com.nonofy.game.presentation.components.board.Grid
import com.nonofy.game.presentation.components.headers.HorizontalBoardHeader
import com.nonofy.game.presentation.components.headers.VerticalBoardHeader

@ExperimentalFoundationApi
@Composable
fun InGameBoard(
    inGameBoardState: InGameBoardState,
    event: (InGameEvent) -> Unit,
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
                .height(IntrinsicSize.Max)
                .fillMaxWidth(0.85f),
            verticalAlignment = Alignment.Bottom
        ) {
            inGameBoardState.verticalHeader.forEach { header ->
                VerticalBoardHeader(
                    header = header,
                    modifier = Modifier
                        .weight(1f)
                        .padding(1.dp)
                        .fillMaxHeight()
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            Column(
                Modifier
                    .weight(0.15f)
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.End
            ) {
                inGameBoardState.horizontalHeader.forEach { header ->
                    HorizontalBoardHeader(
                        header = header,
                        modifier = Modifier
                            .padding(1.dp)
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }
            }

            Grid(
                state = inGameBoardState.gridState,
                event = { event(it) },
                modifier = Modifier.weight(0.85f)
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Preview() {
    InGameBoard(inGameBoardState = InGameBoardState.empty(10), event = { InGameEvent.ResetBoard })
}