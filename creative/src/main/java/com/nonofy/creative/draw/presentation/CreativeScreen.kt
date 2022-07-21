package com.nonofy.creative.draw.presentation

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nonofy.creative.draw.domain.CreativeEvent
import com.nonofy.ui.components.Screen
import com.nonofy.ui.components.grid.Grid
import com.nonofy.ui.components.grid.GridState
import com.nonofy.ui.components.pixel.PixelState


@Composable
fun CreativeScreen() {
    CreativeScreen(creativeViewModel = hiltViewModel())
}

@Composable
private fun CreativeScreen(creativeViewModel: CreativeViewModel) {
    val state by remember(creativeViewModel) { creativeViewModel.state }.collectAsState()

    CreativeScreenContent(state) { creativeViewModel.event(it) }
}

@Composable
private fun CreativeScreenContent(
    state: CreativeScreenState, event: (CreativeEvent) -> Unit
) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Screen {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Grid(
                state = state.gridState,
                onPixelClicked = {
                    event(CreativeEvent.OnClickPixel(it))
                },
                pixelClickWhenFilledEnabled = true
            )

            Button(onClick = {
                val grid = state.gridState.pixels.joinToString(separator = ",") {
                    when (it) {
                        is PixelState.Empty -> "0"
                        else -> "1"
                    }
                }

                clipboardManager.setText(AnnotatedString(grid))

                Toast.makeText(
                    context,
                    "Grid state saved to clipboard",
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Text(text = "Save")
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    CreativeScreenContent(CreativeScreenState(GridState.empty(10)), {})
}