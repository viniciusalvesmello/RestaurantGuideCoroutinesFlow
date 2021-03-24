package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepositoryImpl.RestaurantsRepositoryFactory
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantsRequest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.viewstate.RestaurantsViewState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.launchIO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val repositoryFactory: RestaurantsRepositoryFactory
) : ViewModel() {

    private val repository: RestaurantsRepository by lazy {
        repositoryFactory.create(viewModelScope)
    }

    private val _viewState = MutableStateFlow<RestaurantsViewState?>(null)
    val viewState = _viewState.asStateFlow()

    val request = RestaurantsRequest()
    val pagingData: Flow<PagingData<Restaurant>>
        get() = repository.getRestaurants(request)

    fun getCategoriesRestaurants() = viewModelScope.launchIO {
        _viewState.value = RestaurantsViewState.ListCategoryRestaurants(
            list = repository.getCategoriesRestaurants()
        )
    }
}