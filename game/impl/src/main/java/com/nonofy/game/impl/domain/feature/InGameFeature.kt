package com.nonofy.game.impl.domain.feature

import com.nonofy.game.impl.domain.models.Nonogram

sealed class InGameEvent {
    data class OnPixelClicked(val index: Int) : InGameEvent()
    object ResetBoard : InGameEvent()
    object ClosingGame : InGameEvent()
    object LoadGame : InGameEvent()
}

sealed class InGameEffect {
    data class GameLoaded(val nonogram: Nonogram) : InGameEffect()
    data class GameOver(val nonogram: Nonogram) : InGameEffect()
    object NoChanges : InGameEffect()
    data class CompletedSuccessfully(val nonogram: Nonogram) : InGameEffect()
}