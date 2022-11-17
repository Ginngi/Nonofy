package com.nonofy.game.impl.presentation.mappers

import com.nonofy.game.impl.com.nonofy.game.impl.presentation.mappers.LineStateMapper
import com.nonofy.game.impl.domain.models.Header
import com.nonofy.game.impl.presentation.components.headers.HeaderState
import javax.inject.Inject

class HeaderStateMapper @Inject constructor(
    private val lineStateMapper: LineStateMapper
) {
    fun map(model: Header) = HeaderState(
        lines = model.lines.map { lineStateMapper.map(it) },
        filledPixels = model.filledPixels,
        isCompleted = model.isCompleted
    )
}