package com.nonofy.game.impl.com.nonofy.game.impl.domain.usecase

import com.nonofy.game.impl.domain.GameRepository
import javax.inject.Inject

class HasSavedGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    fun run() = gameRepository.hasSavedGame()
}