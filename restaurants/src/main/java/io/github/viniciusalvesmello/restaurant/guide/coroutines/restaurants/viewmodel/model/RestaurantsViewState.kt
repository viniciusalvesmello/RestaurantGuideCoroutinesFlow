package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.SingleLiveEvent

data class RestaurantsViewState(
    var showLoading: LiveData<Boolean> = SingleLiveEvent(),
    var categories: LiveData<List<CategoryRestaurants>> = SingleLiveEvent(),
    var restaurants: LiveData<List<Restaurant>> = SingleLiveEvent(),
    var restaurantReviews: LiveData<List<RestaurantReview>> = SingleLiveEvent(),
    var showError: LiveData<String> = MediatorLiveData()
)