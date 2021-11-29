package com.nonofy.ui.components.pixelbox

sealed class PixelState {
    object Filled: PixelState()
    object Empty: PixelState()
    object Failed: PixelState()
}