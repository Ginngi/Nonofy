package com.nonofy.game.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonofy.game.domain.models.Solution
import com.nonofy.game.domain.usecase.GetSolutionByIdUseCase
import com.nonofy.game.presentation.components.board.BoardState
import com.nonofy.game.presentation.components.ingameboard.nonogramTestData
import com.nonofy.ui.components.pixelbox.PixelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InGameViewModel @Inject constructor(
    private val getSolutionByIdUseCase: GetSolutionByIdUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<InGameState> = MutableStateFlow(InGameState.LoadingGame)
    val state: StateFlow<InGameState> = _state

    private val _sideEffect = Channel<InGameSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private lateinit var solution: Solution

    init {
        viewModelScope.launch {
            delay(1000)
            _state.emit(InGameState.ShowingInGameBoard(inGameBoardState = nonogramTestData))
            solution = getSolutionByIdUseCase.run()
        }
    }

    fun intent(interaction: InGameInteraction) {
        viewModelScope.launch {
            val effect = when (interaction) {
                is InGameInteraction.OnPixelClicked -> InGameEffect.UpdatePixelStateAtPosition(
                    interaction.column,
                    interaction.row
                )

                is InGameInteraction.ResetBoard -> InGameEffect.CreateEmptyBoard
            }

            reduceEffect(effect, _state.value)
        }
    }

    private suspend fun reduceEffect(effect: InGameEffect, previousState: InGameState) {
        when (effect) {
            is InGameEffect.UpdatePixelStateAtPosition -> {
                val state: InGameState.ShowingInGameBoard =
                    if (previousState is InGameState.ShowingInGameBoard) previousState else return

                updatePixelStateAtPosition(effect, state)
            }

            is InGameEffect.CreateEmptyBoard -> {
                val state: InGameState.ShowingInGameBoard =
                    if (previousState is InGameState.ShowingInGameBoard) previousState else return

                createEmptyBoardFromPreviousBoard(state)
            }
        }
    }

    private suspend fun updatePixelStateAtPosition(
        effect: InGameEffect.UpdatePixelStateAtPosition,
        previousState: InGameState.ShowingInGameBoard
    ) {
        val boardState: BoardState = previousState.inGameBoardState.boardState

        when (boardState.pixels[effect.column][effect.row]) {
            is PixelState.Empty -> {
                val newPixelState =
                    getNextPixelStateFromCurrent(solution.pixels[effect.column][effect.row])

                val numFilledPixels =
                    if (newPixelState is PixelState.Filled) boardState.numPixelsFilled + 1 else boardState.numPixelsFilled


                val numErrors =
                    if (newPixelState is PixelState.Failed) previousState.numErrors + 1 else previousState.numErrors

                if (numErrors >= 3) {
                    _sideEffect.send(InGameSideEffect.GameOver)
                    return
                }

                val pixels = boardState.pixels.toMutableList().apply {
                    val rowList = this[effect.column].toMutableList()
                    rowList[effect.row] = newPixelState

                    this[effect.column] = rowList
                }

                val newState = previousState.copy(
                    numErrors = numErrors,
                    inGameBoardState = previousState.inGameBoardState.copy(
                        boardState = BoardState(
                            pixels = pixels,
                            numPixelsFilled = numFilledPixels
                        )
                    )
                )

                _sideEffect.send(InGameSideEffect.Empty)
                _state.emit(newState)
            }
            else -> return
        }
    }

    private suspend fun createEmptyBoardFromPreviousBoard(previousState: InGameState.ShowingInGameBoard) {
        _sideEffect.send(InGameSideEffect.Empty)
        _state.emit(
            previousState.copy(
                numErrors = 0,
                inGameBoardState = previousState.inGameBoardState.copy(
                    boardState = BoardState(
                        pixels = nonogramTestData.boardState.pixels,
                        numPixelsFilled = 0
                    )
                )
            )
        )
    }

    private fun getNextPixelStateFromCurrent(correctState: PixelState) = when (correctState) {
        is PixelState.Filled -> PixelState.Filled
        else -> PixelState.Failed
    }
}