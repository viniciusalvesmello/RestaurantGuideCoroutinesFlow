package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.di

import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepositoryImpl

@Module
class CitiesModule {

    @Provides
    fun provideRepository(): CitiesRepository = CitiesRepositoryImpl()
}