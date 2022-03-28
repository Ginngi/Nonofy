package com.nonofy.game.domain.models

enum class Pixel(val value: Int) {
    EMPTY(1), FILLED(2), ERROR(3);

    companion object {
        private val map = values().associateBy(Pixel::value)
        fun fromInt(type: Int) = map[type] ?: EMPTY
    }
}