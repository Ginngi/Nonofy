package com.nonofy.game.impl.data.mapper

import com.nonofy.game.impl.com.nonofy.game.impl.data.mapper.LineMapper
import com.nonofy.game.impl.data.proto.GameEntity
import com.nonofy.game.impl.domain.models.Header
import javax.inject.Inject

class HeaderMapper @Inject constructor(
    private val lineMapper: LineMapper
) {
    fun map(entity: GameEntity.HeaderEntity) = Header(
        lines = entity.linesList.map { lineMapper.map(it) },
        filledPixels = entity.filledPixels,
        isCompleted = entity.isCompleted
    )

    fun map(entity: Header): GameEntity.HeaderEntity = GameEntity.HeaderEntity
        .newBuilder()
        .addAllLines(entity.lines.map { lineMapper.map(it) })
        .setIsCompleted(entity.isCompleted)
        .setFilledPixels(entity.filledPixels)
        .build()
}