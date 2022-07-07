package com.nonofy.game.domain.feature

import com.nonofy.game.domain.models.Nonogram

sealed class InGameEvent {
    data class OnPixelClicked(val index: Int) : InGameEvent()
    object ResetBoard : InGameEvent()
    object ClosingGame : InGameEvent()
    object LoadGame : InGameEvent()
}

sealed class InGameEffect {
    data class GameLoaded(val nonogram: Nonogram) : InGameEffect()
    object GameOver : InGameEffect()
    object NoChanges : InGameEffect()
    object CompletedSuccessfully : InGameEffect()
}