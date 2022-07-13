package com.nonofy.game.data.mapper

import com.nonofy.game.data.proto.GameEntity
import com.nonofy.game.domain.models.Grid
import com.nonofy.game.domain.models.Pixel
import javax.inject.Inject

class GridMapper @Inject constructor() {
    fun map(grid: Grid): GameEntity.GridEntity {
        return GameEntity.GridEntity.newBuilder()
            .addAllPixels(grid.pixels.flatten().map { it.value })
            .setNumFilledPixels(grid.numFilledPixels)
            .setSize(grid.size)
            .build()
    }

    fun map(entity: GameEntity.GridEntity) = Grid(
        pixels = entity.pixelsList.map { Pixel.fromInt(it) }.chunked(entity.size),
        numFilledPixels = entity.numFilledPixels,
        size = entity.size
    )
}