package com.nonofy.creative.draw.presentation.components

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
import com.nonofy.ui.components.grid.Grid
import com.nonofy.ui.components.grid.GridState

@Composable
fun CreativeBoard(
    state: GridState,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (verticalHeader, horizontalHeader, grid) = createRefs()
        val headers = (1..state.size).map { it.toString() }.toList()

        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .constrainAs(horizontalHeader) {
                    top.linkTo(parent.top)
                    start.linkTo(grid.start)
                    end.linkTo(grid.end)
                    width = Dimension.fillToConstraints
                },
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            headers.forEach { header ->
                CreativeBoardHeader(
                    title = header,
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
            verticalArrangement = Arrangement.Center
        ) {
            headers.forEach { header ->
                CreativeBoardHeader(
                    title = header,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }

        Grid(
            state = state,
            onPixelClicked = {},
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
    CreativeBoard(
        GridState.empty()
    )
}