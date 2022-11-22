package com.nonofy.game.impl.presentation.components.completedialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nonofy.game.impl.R
import com.nonofy.game.impl.domain.feature.InGameEvent
import com.nonofy.ui.components.Screen

@Composable
fun CompletedSuccessfullyDialog(event: (event: InGameEvent) -> Unit) {
    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.background)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.h1,
                text = stringResource(R.string.congratulation)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_celebration),
                contentDescription = stringResource(R.string.congratulation),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colors.primary
                ),
                modifier = Modifier.size(112.dp)
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    event(InGameEvent.ResetBoard)
                }
            ) {
                Text(
                    color = MaterialTheme.colors.background,
                    text = stringResource(R.string.next_level),
                    style = MaterialTheme.typography.button,
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    event(InGameEvent.ResetBoard)
                }
            ) {
                Text(
                    color = MaterialTheme.colors.background,
                    text = stringResource(R.string.back_to_menu),
                    style = MaterialTheme.typography.button,
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    Screen() {
        CompletedSuccessfullyDialog(event = {})
    }
}