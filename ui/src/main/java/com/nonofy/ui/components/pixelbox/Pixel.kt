package com.nonofy.ui.components.pixelbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nonofy.ui.theme.Purple700

@Composable
fun Pixel(
    onClickPixel: () -> Unit,
    state: PixelState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .background(getColorFromState(state))
        .aspectRatio(1f)
        .clickable { onClickPixel() }
        .border(1.dp, Purple700)
    )
}

private fun getColorFromState(state: PixelState) = when (state) {
    PixelState.Filled -> Purple700
    PixelState.Empty -> Color.White
    PixelState.Failed -> Color.Gray
}