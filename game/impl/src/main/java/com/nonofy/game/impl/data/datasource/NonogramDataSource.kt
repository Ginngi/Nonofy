package com.nonofy.game.impl.data.datasource

import com.nonofy.game.impl.com.nonofy.game.impl.domain.models.Line
import com.nonofy.game.impl.domain.models.Difficulty
import com.nonofy.game.impl.domain.models.Grid
import com.nonofy.game.impl.domain.models.Header
import com.nonofy.game.impl.domain.models.Nonogram
import com.nonofy.game.impl.domain.models.Pixel
import javax.inject.Inject

class NonogramDataSource @Inject constructor() {
    fun easyTest(): Nonogram {
        val difficulty = Difficulty.EASY
        val specialGrid =
            "1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1"
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
            pixels = pixels.chunked(difficulty.size),
            numFilledPixels = numFilled,
            size = difficulty.size,
        )

        val verticalHeaders = generateVerticalHeadersFromGrid(solution)
        val horizontalHeaders = generateHorizontalHeadersFromGrid(solution)
        val grid = createGrid(verticalHeaders, horizontalHeaders, difficulty)

        return Nonogram.empty(
            title = "Easy Game",
            numErrors = 0,
            grid = grid,
            verticalHeaders = verticalHeaders,
            horizontalHeaders = horizontalHeaders,
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
            pixels = pixels.chunked(difficulty.size),
            numFilledPixels = numFilled,
            size = difficulty.size,
        )

        val verticalHeaders = generateVerticalHeadersFromGrid(solution)
        val horizontalHeaders = generateHorizontalHeadersFromGrid(solution)
        val grid = createGrid(verticalHeaders, horizontalHeaders, difficulty)

        return Nonogram.empty(
            title = "Kostas Special Game",
            numErrors = 0,
            grid = grid,
            verticalHeaders = verticalHeaders,
            horizontalHeaders = horizontalHeaders,
            solution = solution,
            difficulty = difficulty
        )
    }

    private fun generateVerticalHeadersFromGrid(grid: Grid): List<Header> {
        val verticalHeaders: MutableList<Header> = mutableListOf()

        for (i in grid.pixels.indices) {
            val headerLines: MutableList<Line> = mutableListOf()
            var numFilledPixel = 0

            for (j in grid.pixels.indices) {
                val pixel = grid.pixels[j][i]
                if (pixel == Pixel.FILLED) {
                    numFilledPixel += 1
                } else {
                    if (numFilledPixel != 0) {
                        headerLines.add(Line(numFilledPixel, false))
                        numFilledPixel = 0
                    }
                }
            }

            if (numFilledPixel != 0) {
                headerLines.add(Line(numFilledPixel, false))
            }

            if (headerLines.isEmpty()) {
                verticalHeaders.add(
                    Header(
                        lines = emptyList(),
                        filledPixels = 0,
                        isCompleted = true
                    )
                )
            } else {
                verticalHeaders.add(
                    Header(
                        lines = headerLines,
                        filledPixels = headerLines.sumOf { it.numberPixels },
                        isCompleted = false
                    )
                )
            }
        }

        return verticalHeaders
    }

    private fun generateHorizontalHeadersFromGrid(grid: Grid): List<Header> {
        val horizontalHeaders: MutableList<Header> = mutableListOf()

        grid.pixels.forEach { pixelRow ->
            val headerLines: MutableList<Line> = mutableListOf()
            var numFilledPixel = 0

            pixelRow.forEach {
                if (it == Pixel.FILLED) {
                    numFilledPixel += 1
                } else {
                    if (numFilledPixel != 0) {
                        headerLines.add(Line(numFilledPixel, false))
                        numFilledPixel = 0
                    }
                }
            }

            if (numFilledPixel != 0) {
                headerLines.add(Line(numFilledPixel, false))
            }

            if (headerLines.isEmpty()) {
                horizontalHeaders.add(
                    Header(
                        lines = emptyList(),
                        filledPixels = 0,
                        isCompleted = true
                    )
                )
            } else {
                horizontalHeaders.add(
                    Header(
                        lines = headerLines,
                        filledPixels = headerLines.sumOf { it.numberPixels },
                        isCompleted = false
                    )
                )
            }
        }
        return horizontalHeaders
    }

    private fun createGrid(
        verticalHeaders: List<Header>,
        horizontalHeaders: List<Header>,
        difficulty: Difficulty
    ): Grid {
        val newBoard: MutableList<MutableList<Pixel>> =
            MutableList(difficulty.size) { MutableList(difficulty.size) { Pixel.EMPTY } }

        verticalHeaders.forEachIndexed { index, header ->
            if (header.isCompleted) {
                newBoard.forEach {
                    if (it[index] == Pixel.EMPTY) {
                        it[index] = Pixel.ERROR
                    }
                }
            }
        }

        horizontalHeaders.forEachIndexed { index, header ->
            if (header.isCompleted) {
                newBoard[index].forEachIndexed { i, pixel ->
                    if (pixel == Pixel.EMPTY) {
                        newBoard[index][i] = Pixel.ERROR
                    }
                }
            }
        }

        return Grid(
            pixels = newBoard,
            numFilledPixels = 0,
            size = difficulty.size
        )
    }
}