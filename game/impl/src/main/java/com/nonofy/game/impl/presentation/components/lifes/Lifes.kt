package com.nonofy.game.impl.presentation.components.lifes

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
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
import com.nonofy.game.impl.domain.feature.performers.NUM_LIFES

@Composable
fun Lifes(
    errors: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in NUM_LIFES - 1 downTo 0) {
            LifeBox(i < errors)
        }
    }
}

@Composable
private fun LifeBox(isDisabled: Boolean) {
    val alpha: Float by animateFloatAsState(
        targetValue = if (isDisabled) 0.3f else 1f,
        animationSpec = if (isDisabled) {
            spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        } else {
            tween(durationMillis = 500)
        }
    )

    Box(
        modifier = Modifier.size(64.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_filled_life),
            contentDescription = stringResource(id = R.string.filled_life),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .size(64.dp)
                .alpha(alpha)
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun ComposablePreview() {
    Lifes(2)
}