package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model

import androidx.annotation.Keep

@Keep
data class RestaurantReviewsResponse(
    val reviews_count: Int,
    val reviews_start: Int,
    val reviews_shown: Int,
    val user_reviews: List<ReviewResponse>
) {
    companion object {
        data class ReviewResponse(
            val review: ReviewDetailResponse
        )

        data class ReviewDetailResponse(
            val rating: Float,
            val review_text: String,
            val rating_text: String,
            val id: Int,
            val review_time_friendly: String,
            val user : UserResponse
        )

        data class UserResponse (
            val name : String,
            val profile_image: String
        )
    }
}