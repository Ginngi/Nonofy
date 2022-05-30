package com.nonofy.home.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeNavigator: HomeNavigator
) : ViewModel() {

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ClickedEasyLevel -> homeNavigator.navigateToGame()
            HomeEvent.ClickedMediumLevel -> homeNavigator.navigateToGame()
            HomeEvent.ClickedHardLevel -> homeNavigator.navigateToGame()
            HomeEvent.ClickedCreativeMode -> homeNavigator.navigateToCreativeMode()
        }
    }
}

sealed class HomeEvent {
    object ClickedEasyLevel : HomeEvent()
    object ClickedMediumLevel : HomeEvent()
    object ClickedHardLevel : HomeEvent()
    object ClickedCreativeMode : HomeEvent()
}