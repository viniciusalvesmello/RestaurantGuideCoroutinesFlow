package io.github.viniciusalvesmello.cache.cities

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.viniciusalvesmello.cache.AppDatabase
import io.github.viniciusalvesmello.cache.cities.room.CityDao

@Module
@InstallIn(ViewModelComponent::class)
class CitiesCacheModule {
    @Provides
    fun providesCitiesDao(appDatabase: AppDatabase): CityDao = appDatabase.cityDao()
}