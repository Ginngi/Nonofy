package com.nonofy.ui.components.pixel

import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getSystemService
import com.nonofy.ui.theme.BlackJapanese
import com.nonofy.ui.theme.RedJapanese
import com.nonofy.ui.theme.WhiteJapanese

@Composable
fun Pixel(
    onClickPixel: () -> Unit,
    state: PixelState,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val vibrator: Vibrator = getSystemService(context, Vibrator::class.java) as Vibrator

    Box(modifier = modifier
        .background(getColorFromState(state))
        .aspectRatio(1f)
        .clickable(enabled = state == PixelState.Empty) {
            vibrator.vibrate(150)
            onClickPixel()
        }
    )
}

private fun getColorFromState(state: PixelState) = when (state) {
    PixelState.Filled -> RedJapanese
    PixelState.Empty -> WhiteJapanese
    PixelState.Failed -> BlackJapanese
}

@Preview
@Composable
fun ComposablePreview() {
    Pixel(
        onClickPixel = {},
        state = PixelState.Empty
    )
}