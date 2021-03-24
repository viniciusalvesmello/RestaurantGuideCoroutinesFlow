package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.mapper.toRestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import javax.inject.Inject

interface RestaurantReviewsRepository {
    suspend fun getRestaurantReviews(id: Int): List<RestaurantReview>
}

class RestaurantReviewsRepositoryImpl @Inject constructor(
    private val service: ZomatoService
) : RestaurantReviewsRepository {
    override suspend fun getRestaurantReviews(id: Int): List<RestaurantReview> = service.getRestaurantReviews(
        id = id,
        count = GET_RESTAURANTS_REVIEWS_COUNT,
        start = GET_RESTAURANTS_REVIEWS_START
    ).user_reviews.map { response ->
        response.review.toRestaurantReview()
    }

    companion object {
        private const val GET_RESTAURANTS_REVIEWS_COUNT = 50
        private const val GET_RESTAURANTS_REVIEWS_START = 0
    }
}