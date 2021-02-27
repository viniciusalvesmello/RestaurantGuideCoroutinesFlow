package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.navigation.AppNavigationImpl
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation

@Module
@InstallIn(SingletonComponent::class)
class AppNavigationModule {

    @Provides
    fun provideAppNavigation(): AppNavigation = AppNavigationImpl()
}