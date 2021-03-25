package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)

    @Provides
    open fun provideRetrofit(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.ZomatoApiUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
}