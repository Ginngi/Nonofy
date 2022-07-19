package com.nonofy.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nonofy.game.api.GameComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeNavigator: HomeNavigator,
    gameComponent: GameComponent
) : ViewModel() {

    val hasSavedGame: StateFlow<Boolean> = gameComponent.hasGameSaved().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        false
    )

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ClickedEasyLevel -> homeNavigator.navigateToGame("easy")
            HomeEvent.ClickedMediumLevel -> homeNavigator.navigateToGame("medium")
            HomeEvent.ClickedHardLevel -> homeNavigator.navigateToGame("hard")
            HomeEvent.ClickedCreativeMode -> homeNavigator.navigateToCreativeMode()
            HomeEvent.ClickedContinueGame -> homeNavigator.navigateToSavedGame()
        }
    }
}

sealed class HomeEvent {
    object ClickedEasyLevel : HomeEvent()
    object ClickedMediumLevel : HomeEvent()
    object ClickedHardLevel : HomeEvent()
    object ClickedCreativeMode : HomeEvent()
    object ClickedContinueGame : HomeEvent()
}