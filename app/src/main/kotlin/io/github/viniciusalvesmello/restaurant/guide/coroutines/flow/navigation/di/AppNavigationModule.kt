package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.navigation.di

import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.navigation.AppNavigationImpl
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation

@Module
class AppNavigationModule {

    @Provides
    fun provideAppNavigation(): AppNavigation = AppNavigationImpl()
}