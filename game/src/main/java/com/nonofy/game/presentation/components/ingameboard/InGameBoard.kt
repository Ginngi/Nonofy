package com.nonofy.game.presentation.components.ingameboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.nonofy.game.domain.feature.InGameEvent
import com.nonofy.game.domain.models.Difficulty
import com.nonofy.game.presentation.components.headers.HorizontalBoardHeader
import com.nonofy.game.presentation.components.headers.VerticalBoardHeader
import com.nonofy.ui.components.grid.Grid

@Composable
fun InGameBoard(
    inGameBoardState: InGameBoardState,
    event: (InGameEvent) -> Unit
) {
    ConstraintLayout {
        val (verticalHeader, horizontalHeader, grid) = createRefs()

        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .padding(bottom = 4.dp)
                .constrainAs(horizontalHeader) {
                    top.linkTo(parent.top)
                    start.linkTo(grid.start)
                    end.linkTo(grid.end)
                    width = Dimension.fillToConstraints
                },
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            inGameBoardState.verticalHeader.forEach { header ->
                VerticalBoardHeader(
                    header = header,
                    difficulty = inGameBoardState.difficulty,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }
        }

        Column(
            Modifier
                .width(IntrinsicSize.Max)
                .padding(end = 4.dp)
                .constrainAs(verticalHeader) {
                    start.linkTo(parent.start)
                    top.linkTo(grid.top)
                    bottom.linkTo(grid.bottom)
                    height = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            inGameBoardState.horizontalHeader.forEach { header ->
                HorizontalBoardHeader(
                    header = header,
                    difficulty = inGameBoardState.difficulty,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }

        Grid(
            state = inGameBoardState.gridState,
            onPixelClicked = { position ->
                event(InGameEvent.OnPixelClicked(position))
            },
            modifier = Modifier
                .constrainAs(grid) {
                    top.linkTo(horizontalHeader.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(verticalHeader.end)
                    width = Dimension.preferredWrapContent
                }
        )

    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Preview() {
    InGameBoard(
        inGameBoardState = InGameBoardState.empty(Difficulty.MEDIUM),
        event = { InGameEvent.ResetBoard }
    )
}