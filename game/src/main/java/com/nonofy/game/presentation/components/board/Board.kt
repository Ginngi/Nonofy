package com.nonofy.game.presentation.components.board

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nonofy.game.presentation.InGameInteraction
import com.nonofy.ui.components.pixelbox.Pixel
import com.nonofy.ui.theme.Purple700

@Composable
fun Board(
    state: BoardState,
    intentsListener: (InGameInteraction) -> Unit,
    modifier: Modifier
) {
    Column(modifier.border(1.dp, Purple700)) {
        state.pixels.forEachIndexed { i, row ->
            Row {
                row.forEachIndexed { j, pixelState ->
                    Pixel(
                        state = pixelState,
                        onClickPixel = { intentsListener(InGameInteraction.OnPixelClicked(i, j)) },
                        modifier = Modifier.weight(1f / row.size)
                    )
                }
            }
        }
    }
}