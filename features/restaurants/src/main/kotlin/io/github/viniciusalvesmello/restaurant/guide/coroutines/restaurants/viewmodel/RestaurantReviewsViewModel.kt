package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantReviewsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.viewstate.RestaurantReviewsViewState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.launchIO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RestaurantReviewsViewModel @Inject constructor(
    private val repository: RestaurantReviewsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow<RestaurantReviewsViewState?>(null)
    val viewState = _viewState.asStateFlow()

    fun getRestaurantReviews(id: Int) = viewModelScope.launchIO(success = {
        _viewState.value = RestaurantReviewsViewState.Loading
        _viewState.value = RestaurantReviewsViewState.ListRestaurantReview(
            list = repository.getRestaurantReviews(id = id)
        )
    }, error = {
        _viewState.value = RestaurantReviewsViewState.Error(it)
    })
}