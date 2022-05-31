package com.nonofy.ui.components.pixel

sealed class PixelState {
    object Filled: PixelState()
    object Empty: PixelState()
    object Failed: PixelState()
}