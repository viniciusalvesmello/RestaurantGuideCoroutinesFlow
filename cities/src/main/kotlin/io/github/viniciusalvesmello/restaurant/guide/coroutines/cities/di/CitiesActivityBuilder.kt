package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.CitiesFragment
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.FragmentMapScope

@Module
abstract class CitiesActivityBuilder {

    @FragmentMapScope
    @ContributesAndroidInjector(modules = [
        CitiesModule::class,
        CitiesViewModelModule::class
    ])
    abstract fun bindCitiesFragment(): CitiesFragment
}