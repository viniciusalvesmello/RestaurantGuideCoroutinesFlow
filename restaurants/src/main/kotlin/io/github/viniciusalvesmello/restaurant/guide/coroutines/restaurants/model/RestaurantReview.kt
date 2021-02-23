package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model

import androidx.annotation.Keep

@Keep
data class RestaurantReview(
    val id: Int,
    val reviewText : String,
    val rating: Float,
    val ratingDescription: String,
    val userName: String,
    val userProfileImage: String,
    val dateDescription: String
)