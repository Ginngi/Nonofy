package com.nonofy.game.data.datasource

import com.nonofy.game.domain.models.Difficulty
import com.nonofy.game.domain.models.Grid
import com.nonofy.game.domain.models.Nonogram
import com.nonofy.game.domain.models.Pixel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NonogramDataSource @Inject constructor() {
    fun test(difficulty: Difficulty): Flow<Nonogram> = flow {
        emit(
            Nonogram.empty(
                title = "Test Game",
                numErrors = 0,
                grid = Grid.empty(difficulty),
                solution = Grid(
                    pixels = MutableList(difficulty.size * difficulty.size) {
                        if (it % 2 == 0) Pixel.FILLED else Pixel.EMPTY
                    },
                    numFilledPixels = (difficulty.size * difficulty.size) / 2,
                    size = difficulty.size,
                ),
            )
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

        return Nonogram.empty(
            title = "Kostas Special Game",
            numErrors = 0,
            grid = Grid.empty(difficulty),
            solution = Grid(
                pixels = pixels,
                numFilledPixels = numFilled,
                size = difficulty.size,
            ),
        )
    }
}