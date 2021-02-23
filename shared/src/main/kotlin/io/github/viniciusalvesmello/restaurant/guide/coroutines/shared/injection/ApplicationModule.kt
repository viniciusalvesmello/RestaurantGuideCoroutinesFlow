package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    @ApplicationQualifier
    internal abstract fun provideContext(application: Application): Context

}