package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.di

import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepositoryImpl
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines.AppCoroutines

@Module
class CitiesModule {

    @Provides
    fun provideRepository(appCoroutines: AppCoroutines): CitiesRepository =
        CitiesRepositoryImpl(appCoroutines)
}