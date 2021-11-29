package com.nonofy.game.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nonofy.game.presentation.components.ingameboard.InGameBoard
import com.nonofy.ui.components.LoadingContent
import com.nonofy.ui.components.Screen
import kotlinx.coroutines.flow.collect

@Composable
fun InGameScreen(
    inGameViewModel: InGameViewModel
) {
    val state by remember(inGameViewModel) { inGameViewModel.state }.collectAsState()
    val sideEffect by remember { inGameViewModel.sideEffect }

    LaunchedEffect(true) {
        inGameViewModel.sideEffect.collect {
            when (it) {
                is InGameSideEffect.GameOver -> GameOverDialog(inGameViewModel)
                is InGameSideEffect.Empty -> {
                    /** no-op **/
                }
            }
        }
    }

    Screen(topBarTitle = "In Game") {
        when (val currentState = state) {
            is InGameState.ShowingInGameBoard -> InGameScreenContent(
                state = currentState,
                inGameViewModel = inGameViewModel
            )
            is InGameState.LoadingGame -> LoadingContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun InGameScreenContent(
    state: InGameState.ShowingInGameBoard,
    inGameViewModel: InGameViewModel
) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = "Errors: ${state.numErrors}",
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Filled Pixels: ${state.inGameBoardState.boardState.numPixelsFilled}",
            modifier = Modifier.fillMaxWidth()
        )

        InGameBoard(
            inGameBoardState = state.inGameBoardState,
            boardIntentListener = { inGameViewModel.intent(it) }
        )
    }
}

@Composable
private fun GameOverDialog(viewModel: InGameViewModel) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = {
            Text(text = "Title")
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {

                        viewModel.intent(InGameInteraction.ResetBoard)
                    }
                ) {
                    Text("Dismiss")
                }
            }
        }
    )
}