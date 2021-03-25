package io.github.viniciusalvesmello.cache.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.viniciusalvesmello.cache.AppDatabase

@Module
@InstallIn(ViewModelComponent::class)
class CacheModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.newInstance(context)
}