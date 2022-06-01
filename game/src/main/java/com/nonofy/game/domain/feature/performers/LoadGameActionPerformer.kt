package com.nonofy.game.domain.feature.performers

import com.nonofy.game.domain.feature.InGameEffect
import com.nonofy.game.domain.usecase.LoadGameByIdUseCase
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LoadGameActionPerformer @Inject constructor(
    private val loadGameByIdUseCase: LoadGameByIdUseCase
) : Performer<LoadGameActionPerformer.Params, InGameEffect.GameLoaded>() {

    override fun createObservable(params: Params): Flow<InGameEffect.GameLoaded> =
        loadGameByIdUseCase.run()
            .map {
                InGameEffect.GameLoaded(it)
            }

    data class Params(val id: String)
}