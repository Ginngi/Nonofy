package com.nonofy.game.impl.domain.feature.performers

import com.nonofy.game.impl.com.nonofy.game.impl.domain.usecase.LoadSavedGameUseCase
import com.nonofy.game.impl.domain.feature.InGameEffect
import com.nonofy.game.impl.domain.usecase.LoadGameByIdUseCase
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoadGameActionPerformer @Inject constructor(
    private val loadGameByIdUseCase: LoadGameByIdUseCase,
    private val loadSavedGameUseCase: LoadSavedGameUseCase,
) : Performer<LoadGameActionPerformer.Params, InGameEffect.GameLoaded>() {

    override fun createObservable(params: Params): Flow<InGameEffect.GameLoaded> {
        return if (params.id == SAVED_GAME_ID) {
            loadSavedGameUseCase.run()
        } else {
            loadGameByIdUseCase.run()
        }.map {
            InGameEffect.GameLoaded(it)
        }
    }

    data class Params(val id: String)
}

private const val SAVED_GAME_ID = "saved"