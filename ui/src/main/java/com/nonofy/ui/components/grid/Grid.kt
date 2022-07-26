package com.nonofy.ui.components.grid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.ui.components.pixel.Pixel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid(
    modifier: Modifier = Modifier,
    state: GridState,
    onPixelClicked: (Int) -> Unit,
    pixelClickWhenFilledEnabled: Boolean = false,
) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(3.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(bottomEnd = 8.dp),
        modifier = modifier.background(MaterialTheme.colors.background)
    ) {
        LazyVerticalGrid(
            modifier = modifier
                .background(MaterialTheme.colors.background),
            cells = GridCells.Fixed(state.size)
        ) {
            itemsIndexed(state.pixels) { index, pixelState ->
                Pixel(
                    state = pixelState,
                    onClickPixel = { onPixelClicked(index) },
                    modifier = Modifier.border(0.5.dp, MaterialTheme.colors.primary),
                    pixelClickWhenFilledEnabled = pixelClickWhenFilledEnabled
                )
            }
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    Grid(
        state = GridState.empty(),
        onPixelClicked = {}
    )
}