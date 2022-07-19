package com.nonofy.game.impl

import com.nonofy.game.api.GameComponent
import com.nonofy.game.impl.com.nonofy.game.impl.domain.usecase.HasSavedGameUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameComponentImpl @Inject constructor(
    private val hasSavedGameUseCase: HasSavedGameUseCase
): GameComponent {
    override fun hasGameSaved(): Flow<Boolean> =
        hasSavedGameUseCase.run()
}