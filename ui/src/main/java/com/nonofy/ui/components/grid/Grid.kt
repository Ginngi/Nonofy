package com.nonofy.ui.components.grid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.ui.components.pixel.Pixel


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
            columns = GridCells.Fixed(state.size)
        ) {
            itemsIndexed(state.pixels) { index, pixelState ->
                Row(Modifier.height(IntrinsicSize.Min)) {
                    Column(
                        Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Pixel(
                            state = pixelState,
                            onClickPixel = { onPixelClicked(index) },
                            pixelClickWhenFilledEnabled = pixelClickWhenFilledEnabled
                        )
                        Divider(
                            color = MaterialTheme.colors.primary
                        ) //Horizontal divider
                    }

                    //Vertical divider avoiding the last cell in each row
                    Column() {
                        Divider(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                        )
                    }
                }
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