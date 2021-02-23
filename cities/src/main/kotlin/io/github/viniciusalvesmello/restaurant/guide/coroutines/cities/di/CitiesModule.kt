package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.di

import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
class CitiesModule {

    @Provides
    fun provideRepository(): CitiesRepository = CitiesRepositoryImpl(
        coroutineContext = Dispatchers.IO
    )
}