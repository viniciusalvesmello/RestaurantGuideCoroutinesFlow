package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository.CitiesRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.viewmodel.model.CitiesViewState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.asMutable
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.StateView
import javax.inject.Inject

class CitiesViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
) : ViewModel() {

    private var getCities: MutableLiveData<ResourceResponse<List<City>>> = MediatorLiveData()

    val viewState = CitiesViewState().apply {
        cities = switchMap(getCities) { it.data }
        showLoading = map(switchMap(getCities) { it.state }) { it == StateView.LOADING }
    }

    fun getCities() {
        getCities.postValue(citiesRepository.getCities())
    }
}