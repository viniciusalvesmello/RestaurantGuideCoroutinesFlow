package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.CategoriesRestaurantsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.RestaurantReviewsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.RestaurantsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.HEADER_CONTEXT_TYPE
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_CATEGORY
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_COUNT
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_ENTITY_ID
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_ENTITY_TYPE
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_ORDER
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_RESTAURANT_ID
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_SORT
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.PARAM_START
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.URL_GET_CATEGORIES_RESTAURANTS
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.URL_GET_RESTAURANT_REVIEWS
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.utils.Constants.URL_GET_SEARCH_RESTAURANTS
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ZomatoService {

    @Headers(HEADER_CONTEXT_TYPE)
    @GET(URL_GET_CATEGORIES_RESTAURANTS)
    suspend fun getCategoriesRestaurants(): CategoriesRestaurantsResponse


    @Headers(HEADER_CONTEXT_TYPE)
    @GET(URL_GET_SEARCH_RESTAURANTS)
    suspend fun getRestaurants(
        @Query(PARAM_ENTITY_ID) entityId: Int,
        @Query(PARAM_ENTITY_TYPE) entityType: String,
        @Query(PARAM_SORT) sort: String,
        @Query(PARAM_ORDER) order: String,
        @Query(PARAM_CATEGORY) category: Int,
        @Query(PARAM_COUNT) count: Int,
        @Query(PARAM_START) start: Int
    ): RestaurantsResponse

    @Headers(HEADER_CONTEXT_TYPE)
    @GET(URL_GET_RESTAURANT_REVIEWS)
    suspend fun getRestaurantReviews(
        @Query(PARAM_RESTAURANT_ID) restaurantId: Int,
        @Query(PARAM_COUNT) count: Int,
        @Query(PARAM_START) start: Int
    ): RestaurantReviewsResponse
}