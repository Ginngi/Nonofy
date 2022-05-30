package com.nonofy.creative

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreativeViewModel @Inject constructor(): ViewModel() {
    fun onEvent(event: CreativeEvent) {

    }
}

sealed class CreativeEvent {

}