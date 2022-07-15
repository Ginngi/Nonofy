package com.nonofy.game.impl.data.mapper

import com.nonofy.game.impl.data.proto.GameEntity
import com.nonofy.game.impl.domain.models.Header
import javax.inject.Inject

class HeaderMapper @Inject constructor() {
    fun map(entity: GameEntity.HeaderEntity) = Header(
        value = entity.value,
        filledPixels = entity.filledPixels,
        isCompleted = entity.isCompleted
    )

    fun map(entity: Header): GameEntity.HeaderEntity = GameEntity.HeaderEntity
        .newBuilder()
        .setValue(entity.value)
        .setIsCompleted(entity.isCompleted)
        .setFilledPixels(entity.filledPixels)
        .build()
}