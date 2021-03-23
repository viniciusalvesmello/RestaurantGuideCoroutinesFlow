package io.github.viniciusalvesmello.cache.cities

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.viniciusalvesmello.cache.AppDatabase

@Module
@InstallIn(ViewModelComponent::class)
class CitiesCacheModule {
    @Provides
    fun providesCitiesCacheRepository(appDatabase: AppDatabase): CitiesCacheRepository =
        CitiesCacheRepositoryImpl(cityDao = appDatabase.cityDao())
}