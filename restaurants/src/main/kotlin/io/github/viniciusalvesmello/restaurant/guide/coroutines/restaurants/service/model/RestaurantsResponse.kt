package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model

import androidx.annotation.Keep

@Keep
data class RestaurantsResponse(
    val results_found: Int,
    val results_start: Int,
    val results_shown: Int,
    val restaurants: List<RestaurantResponse>
) {
    companion object {
        data class RestaurantResponse(
            val restaurant: RestaurantDetailResponse
        )

        data class RestaurantDetailResponse(
            val id: Int,
            val name: String,
            val cuisines: String,
            val phone_numbers: String,
            val thumb: String,
            val featured_image: String,
            val user_rating: UserRatingResponse,
            val location: LocationResponse
        )

        data class UserRatingResponse(
            val aggregate_rating: String,
            val rating_text: String
        )

        data class LocationResponse(
            val address: String,
            val latitude: String,
            val longitude: String,
            val locality_verbose: String
        )
    }
}