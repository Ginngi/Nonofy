package com.nonofy.game.impl.data.mapper

import com.nonofy.game.impl.data.proto.GameEntity
import com.nonofy.game.impl.domain.models.Difficulty
import com.nonofy.game.impl.domain.models.Nonogram
import javax.inject.Inject

class NonogramMapper @Inject constructor(
    private val gridMapper: GridMapper,
    private val headerMapper: HeaderMapper,
    private val difficultyMapper: DifficultyMapper
) {
    fun map(model: Nonogram): GameEntity.NonogramEntity =
        GameEntity.NonogramEntity.newBuilder()
            .setTitle(model.title)
            .setNumErrors(model.numErrors)
            .setCurrentGrid(gridMapper.map(model.grid))
            .setSolution(gridMapper.map(model.solution))
            .addAllHorizontalHeaders(model.horizontalHeaders.map { headerMapper.map(it) })
            .addAllVerticalHeaders(model.verticalHeaders.map { headerMapper.map(it) })
            .setDifficulty(
                when (model.difficulty) {
                    Difficulty.EASY -> GameEntity.NonogramEntity.DifficultyEntity.EASY
                    Difficulty.MEDIUM -> GameEntity.NonogramEntity.DifficultyEntity.MEDIUM
                    Difficulty.HARD -> GameEntity.NonogramEntity.DifficultyEntity.HARD
                }
            )
            .build()

    fun map(entity: GameEntity.NonogramEntity): Nonogram {
        val solution = gridMapper.map(entity.solution)

        return Nonogram(
            title = entity.title,
            numErrors = entity.numErrors,
            grid = gridMapper.map(entity.currentGrid),
            solution = solution,
            verticalHeaders = entity.verticalHeadersList.map { headerMapper.map(it) },
            horizontalHeaders = entity.horizontalHeadersList.map { headerMapper.map(it) },
            difficulty = difficultyMapper.map(entity.difficulty)
        )
    }
}