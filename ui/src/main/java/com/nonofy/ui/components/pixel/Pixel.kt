package com.nonofy.ui.components.pixel

import android.os.Build
import android.os.VibrationEffect
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
    pixelClickWhenFilledEnabled: Boolean = false,
) {
    val context = LocalContext.current
    val vibrator: Vibrator = getSystemService(context, Vibrator::class.java) as Vibrator

    Box(modifier = modifier
        .background(getColorFromState(state))
        .aspectRatio(1f)
        .clickable(enabled = !(state != PixelState.Empty && pixelClickWhenFilledEnabled)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        100,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(100)
            }
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