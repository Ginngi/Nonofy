package com.nonofy.game.data.mapper

import com.nonofy.game.data.proto.GameEntity
import com.nonofy.game.domain.models.Difficulty
import com.nonofy.game.domain.models.Grid
import com.nonofy.game.domain.models.Nonogram
import com.nonofy.game.domain.models.Pixel
import javax.inject.Inject

class NonogramMapper @Inject constructor(
    private val gridMapper: GridMapper
) {
    fun map(model: Nonogram): GameEntity.NonogramEntity =
        GameEntity.NonogramEntity.newBuilder()
            .setTitle(model.title)
            .setNumErrors(model.numErrors)
            .setCurrentGrid(gridMapper.map(model.grid))
            .setSolution(gridMapper.map(model.solution))
            .build()

    fun map(entity: GameEntity.NonogramEntity): Nonogram {
        val solution = gridMapper.map(entity.solution)

        return Nonogram(
            title = entity.title,
            numErrors = entity.numErrors,
            grid = gridMapper.map(entity.currentGrid),
            solution = solution,
            verticalHeaders = generateVerticalHeadersFromGrid(solution),
            horizontalHeaders = generateHorizontalHeadersFromGrid(solution),
            difficulty = when(entity.difficulty) {
                GameEntity.NonogramEntity.DifficultyEntity.EASY -> Difficulty.EASY
                GameEntity.NonogramEntity.DifficultyEntity.MEDIUM -> Difficulty.MEDIUM
                GameEntity.NonogramEntity.DifficultyEntity.HARD -> Difficulty.HARD
                else -> Difficulty.MEDIUM
            }
        )
    }

    private fun generateVerticalHeadersFromGrid(grid: Grid): List<String> {
        val verticalHeaders: MutableList<String> = mutableListOf()

        for (i in 0 until grid.size) {
            val column = grid.pixels.slice(i until grid.pixels.size step grid.size)
            val header: MutableList<Int> = mutableListOf()
            var numFilledPixel = 0

            column.forEach {
                if (it == Pixel.FILLED) {
                    numFilledPixel += 1
                } else {
                    if (numFilledPixel != 0) {
                        header.add(numFilledPixel)
                        numFilledPixel = 0
                    }
                }
            }

            if (numFilledPixel != 0) {
                header.add(numFilledPixel)
                numFilledPixel = 0
            }

            verticalHeaders.add(header.joinToString(separator = ","))
            header.clear()
        }

        return verticalHeaders
    }

    private fun generateHorizontalHeadersFromGrid(grid: Grid): List<String> {
        val horizontalHeaders: MutableList<String> = mutableListOf()

        var numFilledPixel = 0
        val header: MutableList<Int> = mutableListOf()

        grid.pixels.forEachIndexed { index, pixel ->
            if (pixel == Pixel.FILLED) {
                numFilledPixel += 1
            } else {
                if (numFilledPixel != 0) {
                    header.add(numFilledPixel)
                    numFilledPixel = 0
                }
            }

            if ((index + 1) % grid.size == 0) {
                if (numFilledPixel != 0) {
                    header.add(numFilledPixel)
                    numFilledPixel = 0
                }
                horizontalHeaders.add(header.joinToString(separator = ","))
                header.clear()
            }
        }

        return horizontalHeaders
    }
}