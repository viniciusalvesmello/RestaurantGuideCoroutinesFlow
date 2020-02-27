package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.di.CitiesActivityBuilder
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.App
import io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.navigation.di.AppNavigationModule
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.di.RestaurantsActivityBuilder
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines.di.AppCoroutinesModule
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ApplicationModule
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ViewModelFactoryModule

@Component(
    modules = ([
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ViewModelFactoryModule::class,
        ClientModule::class,
        ApiModule::class,
        AppNavigationModule::class,
        AppCoroutinesModule::class,
        CitiesActivityBuilder::class,
        RestaurantsActivityBuilder::class
    ])
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}