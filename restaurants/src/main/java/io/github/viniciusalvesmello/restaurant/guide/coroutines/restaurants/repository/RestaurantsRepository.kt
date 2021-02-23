package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import kotlinx.coroutines.CoroutineScope

interface RestaurantsRepository {

    fun getCategoriesRestaurants(
        coroutineScope: CoroutineScope
    ): ResourceResponse<List<CategoryRestaurants>>

    fun getRestaurants(
        coroutineScope: CoroutineScope,
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ): ResourceResponse<List<Restaurant>>

    fun getRestaurantReviews(
        coroutineScope: CoroutineScope,
        restaurantId: Int,
        count: Int,
        start: Int
    ): ResourceResponse<List<RestaurantReview>>
}