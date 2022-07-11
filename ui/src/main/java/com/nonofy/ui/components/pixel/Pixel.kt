package com.nonofy.ui.components.pixel

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import com.nonofy.ui.R
import com.nonofy.ui.theme.RedJapanese

@Composable
fun Pixel(
    onClickPixel: () -> Unit,
    state: PixelState,
    modifier: Modifier = Modifier,
    pixelClickWhenFilledEnabled: Boolean = false,
) {
    val context = LocalContext.current
    Box(modifier = modifier
        .background(getColorFromState(state))
        .aspectRatio(1f)
        .clickable(enabled = !(state != PixelState.Empty && pixelClickWhenFilledEnabled)) {
            vibrate(context)
            onClickPixel()
        }
    ) {
        if (state is PixelState.Failed) {
            Image(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Error",
                colorFilter = ColorFilter.tint(
                    RedJapanese
                ),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

private fun getColorFromState(state: PixelState) = when (state) {
    PixelState.Filled -> RedJapanese
    PixelState.Empty -> Color.Transparent
    PixelState.Failed -> Color.Transparent
}

private fun vibrate(context: Context) {
    val vibrator: Vibrator = getSystemService(context, Vibrator::class.java) as Vibrator

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                20,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        vibrator.vibrate(20)
    }
}

@Preview
@Composable
fun ComposablePreview() {
    Row {
        Pixel(
            onClickPixel = {},
            state = PixelState.Empty,
            modifier = Modifier.size(100.dp)
        )
        Pixel(
            onClickPixel = {},
            state = PixelState.Filled,
            modifier = Modifier.size(100.dp)
        )
        Pixel(
            onClickPixel = {},
            state = PixelState.Failed,
            modifier = Modifier.size(100.dp)
        )
    }
}