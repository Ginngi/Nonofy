package com.nonofy.game.data.mapper

import com.nonofy.game.data.proto.GameEntity
import com.nonofy.game.domain.models.Difficulty
import javax.inject.Inject


class DifficultyMapper @Inject constructor() {
    fun map(entity: GameEntity.NonogramEntity.DifficultyEntity): Difficulty = when (entity) {
        GameEntity.NonogramEntity.DifficultyEntity.EASY -> Difficulty.EASY
        GameEntity.NonogramEntity.DifficultyEntity.MEDIUM -> Difficulty.MEDIUM
        GameEntity.NonogramEntity.DifficultyEntity.HARD -> Difficulty.HARD
        else -> Difficulty.MEDIUM
    }

    fun map(model: Difficulty): GameEntity.NonogramEntity.DifficultyEntity = when (model) {
        Difficulty.EASY -> GameEntity.NonogramEntity.DifficultyEntity.EASY
        Difficulty.MEDIUM -> GameEntity.NonogramEntity.DifficultyEntity.MEDIUM
        Difficulty.HARD -> GameEntity.NonogramEntity.DifficultyEntity.HARD
    }
}