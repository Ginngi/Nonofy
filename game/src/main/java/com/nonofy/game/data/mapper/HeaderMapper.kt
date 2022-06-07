package com.nonofy.game.data.mapper

import com.nonofy.game.data.proto.GameEntity
import com.nonofy.game.domain.models.Header
import javax.inject.Inject

class HeaderMapper @Inject constructor() {
    fun map(entity: GameEntity.HeaderEntity) = Header(
        value = entity.value,
        isCompleted = entity.isCompleted
    )

    fun map(entity: Header): GameEntity.HeaderEntity = GameEntity.HeaderEntity
        .newBuilder()
        .setValue(entity.value)
        .setIsCompleted(entity.isCompleted)
        .build()
}