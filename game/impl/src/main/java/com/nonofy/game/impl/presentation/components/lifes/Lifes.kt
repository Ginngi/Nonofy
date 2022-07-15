package com.nonofy.game.impl.presentation.components.lifes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.impl.R
import com.nonofy.game.impl.domain.feature.performers.NUM_LIFES
import com.nonofy.ui.theme.RedJapanese

@Composable
fun Lifes(
    errors: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in NUM_LIFES - 1 downTo 0) {
            if (i < errors) {
                Icon(
                    painter = painterResource(R.drawable.ic_empty_life),
                    contentDescription = "Empty life",
                    tint = RedJapanese,
                    modifier = Modifier.size(64.dp)
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_filled_life),
                    contentDescription = "Filled life",
                    tint = RedJapanese,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun ComposablePreview() {
    Lifes(2)
}