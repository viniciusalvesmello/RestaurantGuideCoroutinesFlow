package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.di

import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepositoryImpl
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import retrofit2.Retrofit

@Module
class RestaurantsModule {

    @Provides
    fun provideRepository(zomatoService: ZomatoService): RestaurantsRepository =
        RestaurantsRepositoryImpl(zomatoService)

    @Provides
    fun provideContactService(retrofit: Retrofit): ZomatoService =
        retrofit.create(ZomatoService::class.java)
}