package com.nonofy.game.impl.domain.feature

import com.nonofy.game.impl.com.nonofy.game.impl.domain.feature.performers.ChangePixelModeActionPerformer
import com.nonofy.game.impl.domain.feature.performers.LoadGameActionPerformer
import com.nonofy.game.impl.domain.feature.performers.ResetBoardActionPerformer
import com.nonofy.game.impl.domain.feature.performers.SaveGameActionPerformer
import com.nonofy.game.impl.domain.feature.performers.UpdatePixelAtPositionActionPerformer
import com.nonofy.utils.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class InGameLogic @Inject constructor(
    private val loadGameActionPerformer: LoadGameActionPerformer,
    private val updatePixelAtPositionActionPerformer: UpdatePixelAtPositionActionPerformer,
    private val resetBoardActionPerformer: ResetBoardActionPerformer,
    private val saveGameActionPerformer: SaveGameActionPerformer,
    private val changePixelModeActionPerformer: ChangePixelModeActionPerformer,
    private val inGameReducer: InGameReducer,
) : Feature<InGameModel, InGameEvent, InGameEffect>(
    actionPerformers = arrayOf(
        loadGameActionPerformer.flow,
        updatePixelAtPositionActionPerformer.flow,
        resetBoardActionPerformer.flow,
        saveGameActionPerformer.flow,
        changePixelModeActionPerformer.flow
    ),
    reducer = inGameReducer,
    scope = CoroutineScope(Dispatchers.IO),
    initialValue = InGameModel.loading()
) {
    fun init(gameId: String) {
        loadGameActionPerformer(LoadGameActionPerformer.Params(gameId))
    }

    override fun onEvent(event: InGameEvent) {
        when (event) {
            is InGameEvent.OnPixelClicked -> updatePixelAtPositionActionPerformer(
                UpdatePixelAtPositionActionPerformer.Params(
                    model.value.nonogram,
                    event.index,
                    model.value.isPixelEnabled
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

            is InGameEvent.OnClickModeClicked -> changePixelModeActionPerformer(
                ChangePixelModeActionPerformer.Params(event.isPixelEnabled)
            )
        }
    }
}