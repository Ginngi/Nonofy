package com.nonofy.navigation.di

import com.nonofy.home.presentation.HomeNavigator
import com.nonofy.navigation.HomeNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface NavigationModule {
    @Binds
    fun bindHomeNavigator(impl: HomeNavigatorImpl): HomeNavigator
}