package com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.pixelswitcher

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.createChildTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.impl.R
import com.nonofy.game.impl.domain.feature.InGameEvent

@OptIn(ExperimentalTransitionApi::class)
@Composable
fun PixelSwitcher(
    modifier: Modifier = Modifier,
    state: Boolean,
    event: (InGameEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .height(70.dp)
            .padding(4.dp)
    ) {

        val transition = updateTransition(state, label = "")

        PixelModeIcon(
            drawableId = R.drawable.ic_close,
            contentDescriptionId = R.string.click_error_mode,
            transition = transition.createChildTransition {
                if (it) PixelModeState.Collapsed else PixelModeState.Expanded
            },
            onClick = { if (state) event(InGameEvent.OnClickModeClicked(false)) }
        )

        Switch(
            checked = state,
            onCheckedChange = {
                event(InGameEvent.OnClickModeClicked(it))
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                checkedTrackColor = MaterialTheme.colors.primary,
                uncheckedThumbColor = MaterialTheme.colors.primary,
                uncheckedTrackColor = MaterialTheme.colors.primary,
                checkedTrackAlpha = 0.4f,
                uncheckedTrackAlpha = 0.4f,
            )
        )

        PixelModeIcon(
            drawableId = R.drawable.ic_square,
            contentDescriptionId = R.string.click_pixel_mode,
            transition = transition.createChildTransition {
                if (it) PixelModeState.Expanded else PixelModeState.Collapsed
            },
            onClick = { if (!state) event(InGameEvent.OnClickModeClicked(true)) }
        )

    }
}

@Composable
private fun PixelModeIcon(
    @DrawableRes drawableId: Int,
    @StringRes contentDescriptionId: Int,
    transition: Transition<PixelModeState>,
    onClick: () -> Unit
) {
    val size by transition.animateDp(
        transitionSpec = {
            when {
                PixelModeState.Collapsed isTransitioningTo PixelModeState.Expanded ->
                    spring(stiffness = 50f)
                else ->
                    tween(durationMillis = 500)
            }
        }, label = ""
    ) { state ->
        when (state) {
            PixelModeState.Collapsed -> 32.dp
            PixelModeState.Expanded -> 64.dp
        }
    }

    val alpha by transition.animateFloat(
        transitionSpec = {
            when {
                PixelModeState.Collapsed isTransitioningTo PixelModeState.Expanded ->
                    spring(stiffness = 50f)
                else ->
                    tween(durationMillis = 500)
            }
        }, label = ""
    ) { state ->
        when (state) {
            PixelModeState.Collapsed -> 0.4f
            PixelModeState.Expanded -> 1f
        }
    }

    Icon(
        painter = painterResource(id = drawableId),
        contentDescription = stringResource(contentDescriptionId),
        tint = MaterialTheme.colors.primary,
        modifier = Modifier
            .clickable { onClick() }
            .size(size)
            .alpha(alpha)
    )
}

private enum class PixelModeState {
    Collapsed,
    Expanded
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    PixelSwitcher(state = true) {}
}