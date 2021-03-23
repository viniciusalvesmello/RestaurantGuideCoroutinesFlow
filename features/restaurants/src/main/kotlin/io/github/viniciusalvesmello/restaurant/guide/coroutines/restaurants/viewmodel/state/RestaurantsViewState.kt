package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.SingleLiveEvent

data class RestaurantsViewState(
    var showLoading: LiveData<Boolean> = SingleLiveEvent(),
    var categories: LiveData<List<CategoryRestaurants>> = SingleLiveEvent(),
    var restaurantReviews: LiveData<List<RestaurantReview>> = SingleLiveEvent(),
    var showError: LiveData<String> = MediatorLiveData()
)