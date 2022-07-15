package com.nonofy.game.impl.presentation.mappers

import com.nonofy.game.impl.domain.models.Header
import com.nonofy.game.impl.presentation.components.headers.HeaderState
import javax.inject.Inject

class HeaderStateMapper @Inject constructor() {
    fun map(model: Header) = HeaderState(
        value = model.value,
        isCompleted = model.isCompleted
    )
}