package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class RestaurantsModule {

    @Provides
    fun provideContactService(retrofit: Retrofit): ZomatoService =
        retrofit.create(ZomatoService::class.java)
}