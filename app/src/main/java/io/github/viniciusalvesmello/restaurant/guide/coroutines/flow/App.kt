package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.injection.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}