package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.viewstate

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.CategoryRestaurants

sealed class RestaurantsViewState {
    data class ListCategoryRestaurants(val list: List<CategoryRestaurants>): RestaurantsViewState()
}