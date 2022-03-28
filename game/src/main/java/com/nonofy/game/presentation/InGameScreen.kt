package com.nonofy.game.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.nonofy.game.domain.feature.InGameEvent
import com.nonofy.game.presentation.components.ingameboard.InGameBoard
import com.nonofy.game.presentation.components.ingameboard.InGameBoardState
import com.nonofy.ui.components.LoadingContent
import com.nonofy.ui.components.Screen

@ExperimentalFoundationApi
@Composable
fun InGameScreen() {
    InGameScreen(inGameViewModel = hiltViewModel())
}

@ExperimentalFoundationApi
@Composable
private fun InGameScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    inGameViewModel: InGameViewModel
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                inGameViewModel.event(InGameEvent.ClosingGame)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val state by remember(inGameViewModel) { inGameViewModel.state }
        .collectAsState()

    InGameScreen(
        viewState = state,
        event = { inGameViewModel.event(it) }
    )
}

@ExperimentalFoundationApi
@Composable
private fun InGameScreen(
    viewState: InGameState,
    event: (event: InGameEvent) -> Unit
) {
    Screen(topBarTitle = viewState.title) {
        if (viewState.isLoading) {
            LoadingContent()
        } else {
            InGameNonogramScreen(viewState = viewState, event = event)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun InGameNonogramScreen(
    viewState: InGameState,
    event: (event: InGameEvent) -> Unit
) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Errors: ${viewState.numErrors}",
            modifier = Modifier.fillMaxWidth()
        )

        if (viewState.inGameBoardState != null) {
            InGameBoard(
                inGameBoardState = viewState.inGameBoardState,
                event = event
            )
        }
    }

    if (viewState.isGameOver) GameOverDialog(event = event)
}

@Composable
private fun GameOverDialog(event: (event: InGameEvent) -> Unit) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = {
            Text(text = "Game Over")
        },
        buttons = {
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
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
fun ComposablePreview() {
    InGameScreen(viewState = InGameState(
        title = "Preview",
        numErrors = 0,
        isLoading = false,
        isGameOver = false,
        inGameBoardState = InGameBoardState.empty(10)
    ), event = {})
}