package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.RestaurantsViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ViewModelKey

@Module
abstract class RestaurantsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RestaurantsViewModel::class)
    abstract fun bindContactViewModel(contactViewModel: RestaurantsViewModel): ViewModel
}