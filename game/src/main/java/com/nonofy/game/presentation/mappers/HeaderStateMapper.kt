package com.nonofy.game.presentation.mappers

import com.nonofy.game.domain.models.Header
import com.nonofy.game.presentation.components.headers.HeaderState
import javax.inject.Inject

class HeaderStateMapper @Inject constructor() {
    fun map(model: Header) = HeaderState(
        value = model.value,
        isCompleted = model.isCompleted
    )
}