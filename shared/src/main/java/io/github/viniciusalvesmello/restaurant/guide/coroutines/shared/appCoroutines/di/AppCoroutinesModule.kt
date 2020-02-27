package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines.di

import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines.AppCoroutines
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines.AppCoroutinesImpl

@Module
class AppCoroutinesModule {

    @Provides
    fun provideAppCoroutines(): AppCoroutines = AppCoroutinesImpl()
}