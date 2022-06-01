package com.nonofy.game.domain.feature

import com.nonofy.game.domain.feature.performers.LoadGameActionPerformer
import com.nonofy.game.domain.feature.performers.ResetBoardActionPerformer
import com.nonofy.game.domain.feature.performers.SaveGameActionPerformer
import com.nonofy.game.domain.feature.performers.UpdatePixelAtPositionActionPerformer
import com.nonofy.utils.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class InGameLogic @Inject constructor(
    private val loadGameActionPerformer: LoadGameActionPerformer,
    private val updatePixelAtPositionActionPerformer: UpdatePixelAtPositionActionPerformer,
    private val resetBoardActionPerformer: ResetBoardActionPerformer,
    private val saveGameActionPerformer: SaveGameActionPerformer,
    inGameReducer: InGameReducer,
) : Feature<InGameModel, InGameEvent, InGameEffect>(
    actionPerformers = arrayOf(
        loadGameActionPerformer.flow,
        updatePixelAtPositionActionPerformer.flow,
        resetBoardActionPerformer.flow,
        saveGameActionPerformer.flow
    ),
    reducer = inGameReducer,
    scope = CoroutineScope(Dispatchers.IO),
    initialValue = InGameModel.loading()
) {
    fun init() {
        loadGameActionPerformer(LoadGameActionPerformer.Params(""))
    }

    override fun onEvent(event: InGameEvent) {
        when (event) {
            is InGameEvent.OnPixelClicked -> updatePixelAtPositionActionPerformer(
                UpdatePixelAtPositionActionPerformer.Params(
                    model.value.nonogram,
                    event.index
                )
            )
            is InGameEvent.ResetBoard -> resetBoardActionPerformer(
                ResetBoardActionPerformer.Params(
                    model.value.nonogram
                )
            )
            is InGameEvent.ClosingGame -> saveGameActionPerformer(
                SaveGameActionPerformer.Params(
                    model.value.nonogram
                )
            )

            is InGameEvent.LoadGame -> loadGameActionPerformer(
                LoadGameActionPerformer.Params("")
            )
        }
    }
}