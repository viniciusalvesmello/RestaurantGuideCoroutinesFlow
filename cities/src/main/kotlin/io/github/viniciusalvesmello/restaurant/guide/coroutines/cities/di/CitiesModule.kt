package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepositoryImpl
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class CitiesModule {

    @Provides
    fun provideRepository(): CitiesRepository = CitiesRepositoryImpl(
        coroutineContext = Dispatchers.IO
    )
}