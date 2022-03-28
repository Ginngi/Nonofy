package com.nonofy.game.di

import com.nonofy.game.data.GameRepositoryImpl
import com.nonofy.game.domain.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class InGameModule {
    @Provides
    fun providesGameRepository(impl: GameRepositoryImpl): GameRepository = impl
}