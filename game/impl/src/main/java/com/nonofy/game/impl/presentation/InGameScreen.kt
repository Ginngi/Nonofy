package com.nonofy.game.impl.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.pixelswitcher.PixelSwitcher
import com.nonofy.game.impl.domain.feature.InGameEvent
import com.nonofy.game.impl.domain.models.Difficulty
import com.nonofy.game.impl.presentation.components.completedialog.CompletedSuccessfullyDialog
import com.nonofy.game.impl.presentation.components.gameoverdialog.GameOverDialog
import com.nonofy.game.impl.presentation.components.ingameboard.InGameBoard
import com.nonofy.game.impl.presentation.components.ingameboard.InGameBoardState
import com.nonofy.game.impl.presentation.components.lifes.Lifes
import com.nonofy.ui.components.LoadingContent
import com.nonofy.ui.components.Screen

@Composable
fun InGameScreen() {
    InGameScreen(inGameViewModel = hiltViewModel())
}

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

@Composable
private fun InGameScreen(
    viewState: InGameState,
    event: (event: InGameEvent) -> Unit
) {
    Screen {
        if (viewState.isLoading) {
            LoadingContent()
        } else {
            InGameNonogramScreen(viewState = viewState, event = event)
        }
    }
}

@Composable
private fun InGameNonogramScreen(
    viewState: InGameState,
    event: (event: InGameEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Lifes(
            errors = viewState.numErrors,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        )

        if (viewState.inGameBoardState != null) {
            InGameBoard(
                inGameBoardState = viewState.inGameBoardState,
                event = event,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        PixelSwitcher(
            event = event,
            modifier = Modifier
                .align(CenterHorizontally),
            state = viewState.isPixelModeEnabled
        )
    }

    if (viewState.isGameOver) GameOverDialog(event = event)
    if (viewState.isCompletedSuccessfully) CompletedSuccessfullyDialog(event = event)
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
        inGameBoardState = InGameBoardState.empty(Difficulty.MEDIUM)
    ), event = {})
}