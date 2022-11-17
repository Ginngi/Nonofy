package com.nonofy.game.impl.com.nonofy.game.impl.data.mapper

import com.nonofy.game.impl.com.nonofy.game.impl.domain.models.Line
import com.nonofy.game.impl.data.proto.GameEntity.LineEntity
import javax.inject.Inject

class LineMapper @Inject constructor() {
    fun map(entity: LineEntity): Line = Line(
        numberPixels = entity.numPixels,
        isCompleted = entity.isCompleted
    )

    fun map(model: Line): LineEntity = LineEntity.newBuilder()
        .setNumPixels(model.numberPixels)
        .setIsCompleted(model.isCompleted)
        .build()
}