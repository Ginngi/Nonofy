package com.nonofy.game.presentation.components.gameoverdialog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.nonofy.game.domain.feature.InGameEvent
import com.nonofy.ui.components.Screen

@Composable
fun GameOverDialog(event: (event: InGameEvent) -> Unit) {
    Dialog(onDismissRequest = {}) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Game Over")
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        event(InGameEvent.ResetBoard)
                    }
                ) {
                    Text("Start again?")
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    Screen() {
        GameOverDialog(event = {})
    }
}