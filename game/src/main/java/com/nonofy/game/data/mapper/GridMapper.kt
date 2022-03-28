package com.nonofy.game.data.mapper

import com.nonofy.game.data.proto.Game
import com.nonofy.game.domain.models.Grid
import com.nonofy.game.domain.models.Pixel
import javax.inject.Inject

class GridMapper @Inject constructor() {
    fun map(grid: Grid): Game.GridEntity {
        return Game.GridEntity.newBuilder()
            .addAllPixels(grid.pixels.map { it.value })
            .setNumFilledPixels(grid.numFilledPixels)
            .setSize(grid.size)
            .build()
    }

    fun map(entity: Game.GridEntity) = Grid(
        pixels = entity.pixelsList.map { Pixel.fromInt(it) },
        numFilledPixels = entity.numFilledPixels,
        size = entity.size
    )
}