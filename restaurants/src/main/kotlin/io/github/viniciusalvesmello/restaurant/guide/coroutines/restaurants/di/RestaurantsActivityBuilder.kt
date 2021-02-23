package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.RestaurantDetailsFragment
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.RestaurantsFragment
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.FragmentMapScope

@Module
abstract class RestaurantsActivityBuilder {

    @FragmentMapScope
    @ContributesAndroidInjector(modules = [
        RestaurantsModule::class,
        RestaurantsViewModelModule::class
    ])
    abstract fun bindRestaurantsFragment(): RestaurantsFragment


    @FragmentMapScope
    @ContributesAndroidInjector(modules = [
        RestaurantsModule::class,
        RestaurantsViewModelModule::class
    ])
    abstract fun bindRestaurantDetailsFragment(): RestaurantDetailsFragment
}