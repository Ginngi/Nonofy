package com.nonofy.ui.components.pixelbox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.ui.theme.BlackPaperTransparency
import com.nonofy.ui.theme.Blue
import com.nonofy.ui.theme.Red
import com.nonofy.ui.theme.WhitePaperTransparency60

@Composable
fun Pixel(
    onClickPixel: () -> Unit,
    state: PixelState,
    modifier: Modifier = Modifier,
    isDarkThemeEnabled: Boolean = isSystemInDarkTheme()
) {
    Box(modifier = modifier
        .background(getColorFromState(state, isDarkThemeEnabled))
        .aspectRatio(1f)
        .clickable { onClickPixel() }
    )
}

private fun getColorFromState(state: PixelState, isDarkThemeEnabled: Boolean) = when (state) {
    PixelState.Filled -> Blue
    PixelState.Empty -> if (isDarkThemeEnabled) WhitePaperTransparency60 else BlackPaperTransparency
    PixelState.Failed -> Red
}

@Preview
@Composable
fun ComposablePreview() {
    Pixel(
        onClickPixel = {},
        state = PixelState.Empty
    )
}