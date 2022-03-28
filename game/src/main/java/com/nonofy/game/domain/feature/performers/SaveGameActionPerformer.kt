package com.nonofy.game.domain.feature.performers

import com.nonofy.game.domain.Performer
import com.nonofy.game.domain.feature.InGameEffect
import com.nonofy.game.domain.models.Nonogram
import com.nonofy.game.domain.usecase.SaveGameUseCase
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