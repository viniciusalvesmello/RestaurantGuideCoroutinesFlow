package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.viewmodel.CitiesViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ViewModelKey

@Module
abstract class CitiesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CitiesViewModel::class)
    abstract fun bindCitiesViewModel(citiesViewModel: CitiesViewModel): ViewModel
}