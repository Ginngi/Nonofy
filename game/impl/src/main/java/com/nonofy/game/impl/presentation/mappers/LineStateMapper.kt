package com.nonofy.game.impl.com.nonofy.game.impl.presentation.mappers

import com.nonofy.game.impl.com.nonofy.game.impl.domain.models.Line
import com.nonofy.game.impl.com.nonofy.game.impl.presentation.components.headers.LineState
import javax.inject.Inject

class LineStateMapper @Inject constructor() {
    fun map(model: Line) = LineState(
        numberPixels = model.numberPixels,
        isCompleted = model.isCompleted
    )
}