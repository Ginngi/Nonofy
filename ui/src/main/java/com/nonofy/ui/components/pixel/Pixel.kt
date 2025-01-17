package com.nonofy.ui.components.pixel

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Pixel(
    onClickPixel: () -> Unit,
    state: PixelState,
    modifier: Modifier = Modifier,
    pixelClickWhenFilledEnabled: Boolean = false,
) {
    val context = LocalContext.current

    val animatedBackground = animateColorAsState(
        targetValue = getColorFromState(state = state),
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 50,
            easing = LinearOutSlowInEasing
        )
    )

    Box(modifier = modifier
        .background(animatedBackground.value)
        .aspectRatio(1f)
        .clickable(enabled = !(state != PixelState.Empty && pixelClickWhenFilledEnabled)) {
            vibrate(context)
            onClickPixel()
        }
    ) {
        AnimatedVisibility(
            visible = state == PixelState.Failed,
            enter = scaleIn(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)),
            exit = scaleOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "Error",
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colors.primary
                ),
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
private fun getColorFromState(state: PixelState) = when (state) {
    PixelState.Filled -> MaterialTheme.colors.primary
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