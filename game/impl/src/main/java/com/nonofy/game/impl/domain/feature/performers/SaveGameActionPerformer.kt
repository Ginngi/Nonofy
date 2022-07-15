package com.nonofy.game.impl.domain.feature.performers

import com.nonofy.game.impl.domain.feature.InGameEffect
import com.nonofy.game.impl.domain.models.Nonogram
import com.nonofy.game.impl.domain.usecase.SaveGameUseCase
import com.nonofy.utils.Performer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveGameActionPerformer @Inject constructor(
    private val saveGameUseCase: SaveGameUseCase
) : Performer<SaveGameActionPerformer.Params, InGameEffect.NoChanges>() {

    override fun createObservable(params: Params): Flow<InGameEffect.NoChanges> = flow {
        saveGameUseCase.run(params.nonogram)
        emit(InGameEffect.NoChanges)
    }

    data class Params(val nonogram: Nonogram)
}