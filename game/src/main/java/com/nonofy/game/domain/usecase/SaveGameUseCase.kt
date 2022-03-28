package com.nonofy.game.domain.usecase

import com.nonofy.game.domain.GameRepository
import com.nonofy.game.domain.models.Nonogram
import javax.inject.Inject

class SaveGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun run(nonogram: Nonogram) = repository.saveGame(nonogram)
}