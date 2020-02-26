package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.viewmodel.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City

data class CitiesViewState(
    var showLoading: LiveData<Boolean> = MediatorLiveData(),
    var cities: LiveData<List<City>> = MediatorLiveData()
)