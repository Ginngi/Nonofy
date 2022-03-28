package com.nonofy.game.presentation.components.board

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.domain.feature.InGameEvent
import com.nonofy.ui.components.pixelbox.Pixel
import com.nonofy.ui.theme.WhitePaper

@ExperimentalFoundationApi
@Composable
fun Grid(
    state: GridState,
    event: (InGameEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        state.pixels.forEachIndexed { i, row ->
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                row.forEachIndexed { j, pixelState ->
                    Pixel(
                        state = pixelState,
                        onClickPixel = { event(InGameEvent.OnPixelClicked("$i$j".toInt())) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(1.dp)
                            .clip(RoundedCornerShape(2.dp))
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    Grid(
        state = GridState.empty(10),
        event = { InGameEvent.ResetBoard },
        modifier = Modifier.background(WhitePaper)
    )
}