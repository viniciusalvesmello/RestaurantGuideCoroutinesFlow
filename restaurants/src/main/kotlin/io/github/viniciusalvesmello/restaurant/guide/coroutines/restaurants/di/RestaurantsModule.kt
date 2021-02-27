package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepositoryImpl
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class RestaurantsModule {

    @Provides
    fun provideRepository(zomatoService: ZomatoService): RestaurantsRepository =
        RestaurantsRepositoryImpl(
            zomatoService = zomatoService,
            coroutineContext = Dispatchers.IO
        )

    @Provides
    fun provideContactService(retrofit: Retrofit): ZomatoService =
        retrofit.create(ZomatoService::class.java)
}