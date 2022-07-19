package com.nonofy.game.impl.di

import com.nonofy.game.api.GameComponent
import com.nonofy.game.impl.GameComponentImpl
import com.nonofy.game.impl.data.GameRepositoryImpl
import com.nonofy.game.impl.domain.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class InGameModule {
    @Provides
    fun providesGameRepository(impl: GameRepositoryImpl): GameRepository = impl

    @Provides
    fun providesGameComponent(impl: GameComponentImpl): GameComponent = impl
}