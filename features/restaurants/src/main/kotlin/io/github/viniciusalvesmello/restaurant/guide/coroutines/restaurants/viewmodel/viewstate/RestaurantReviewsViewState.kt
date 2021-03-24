package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.viewstate

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview

sealed class RestaurantReviewsViewState {
    object Loading: RestaurantReviewsViewState()
    data class Error(val throwable: Throwable): RestaurantReviewsViewState()
    data class ListRestaurantReview(val list: List<RestaurantReview>): RestaurantReviewsViewState()
}
