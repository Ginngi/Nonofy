package com.nonofy.game.impl.domain.usecase

import com.nonofy.game.impl.domain.GameRepository
import com.nonofy.game.impl.domain.models.Nonogram
import javax.inject.Inject

class SaveGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun run(nonogram: Nonogram) = repository.saveGame(nonogram)
}