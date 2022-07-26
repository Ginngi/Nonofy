package com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.pixelswitcher

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nonofy.game.impl.R
import com.nonofy.game.impl.domain.feature.InGameEvent

@Composable
fun PixelSwitcher(
    modifier: Modifier = Modifier,
    state: Boolean,
    event: (InGameEvent) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = stringResource(R.string.game_over),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(48.dp)
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
                checkedTrackAlpha = 0.5f,
                uncheckedTrackAlpha = 0.5f,
            )
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_square),
            contentDescription = stringResource(R.string.game_over),
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(40.dp)
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    PixelSwitcher(state = true) {}
}