package com.nonofy.creative.draw.domain

sealed class CreativeEvent {
    data class OnClickPixel(val position: Int) : CreativeEvent()
}

sealed class CreativeEffect {
}