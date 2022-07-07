package com.nonofy.game.data.datasource

import com.nonofy.game.domain.models.Difficulty
import com.nonofy.game.domain.models.Grid
import com.nonofy.game.domain.models.Header
import com.nonofy.game.domain.models.Nonogram
import com.nonofy.game.domain.models.Pixel
import javax.inject.Inject

class NonogramDataSource @Inject constructor() {
    fun easyTest(): Nonogram {
        val difficulty = Difficulty.EASY
        val specialGrid = List(difficulty.size * difficulty.size) { if (it == 0 || it == 1) "1" else "0" }

        val numFilled = specialGrid.count { it == "1" }

        val pixels = specialGrid.map {
            if (it == "1") {
                Pixel.FILLED
            } else {
                Pixel.EMPTY
            }
        }

        val solution = Grid(
            pixels = pixels,
            numFilledPixels = numFilled,
            size = difficulty.size,
        )

        return Nonogram.empty(
            title = "Easy Game",
            numErrors = 0,
            grid = Grid.empty(difficulty),
            verticalHeaders = generateVerticalHeadersFromGrid(solution),
            horizontalHeaders = generateHorizontalHeadersFromGrid(solution),
            solution = solution,
            difficulty = difficulty
        )
    }

    fun kostasGame(): Nonogram {
        val difficulty = Difficulty.HARD
        val specialGrid =
            "0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,0,1,1,1,0,0,1,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,1,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1"
                .split(",")

        val numFilled = specialGrid.count { it == "1" }

        val pixels = specialGrid.map {
            if (it == "1") {
                Pixel.FILLED
            } else {
                Pixel.EMPTY
            }
        }

        val solution = Grid(
            pixels = pixels,
            numFilledPixels = numFilled,
            size = difficulty.size,
        )

        return Nonogram.empty(
            title = "Kostas Special Game",
            numErrors = 0,
            grid = Grid.empty(difficulty),
            verticalHeaders = generateVerticalHeadersFromGrid(solution),
            horizontalHeaders = generateHorizontalHeadersFromGrid(solution),
            solution = solution,
            difficulty = difficulty
        )
    }

    private fun generateVerticalHeadersFromGrid(grid: Grid): List<Header> {
        val verticalHeaders: MutableList<Header> = mutableListOf()

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

            if (header.isEmpty()) {
                verticalHeaders.add(
                    Header(
                        value = "0",
                        isCompleted = true
                    )
                )
            } else {
                verticalHeaders.add(
                    Header(
                        value = header.joinToString(separator = ","),
                        isCompleted = false
                    )
                )
            }
            header.clear()
        }

        return verticalHeaders
    }

    private fun generateHorizontalHeadersFromGrid(grid: Grid): List<Header> {
        val horizontalHeaders: MutableList<Header> = mutableListOf()

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

                if (header.isEmpty()) {
                    horizontalHeaders.add(
                        Header(
                            value = "0",
                            isCompleted = true
                        )
                    )
                } else {
                    horizontalHeaders.add(
                        Header(
                            value = header.joinToString(separator = ","),
                            isCompleted = false
                        )
                    )
                }

                header.clear()
            }
        }

        return horizontalHeaders
    }
}