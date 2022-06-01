package com.nonofy.ui.components.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.ui.components.pixel.Pixel
import com.nonofy.ui.theme.RedJapanese

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid(
    state: GridState,
    onPixelClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier
            .background(RedJapanese)
            .border(5.dp, RedJapanese),
        cells = GridCells.Fixed(state.size)
    ) {
        itemsIndexed(state.pixels) { index, pixelState ->
            Pixel(
                state = pixelState,
                onClickPixel = { onPixelClicked(index) },
                modifier = Modifier.border(0.5.dp, RedJapanese)
            )
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