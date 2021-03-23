package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.mapper

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.CategoriesRestaurantsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.RestaurantReviewsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.RestaurantsResponse

fun CategoriesRestaurantsResponse.Companion.CategoryResponse.toCategoryRestaurants() =
    CategoryRestaurants(
        id = id,
        name = name
    )

fun RestaurantsResponse.Companion.RestaurantDetailResponse.toRestaurant() =
    Restaurant(
        id = id,
        name = name,
        cuisines = cuisines,
        phoneNumbers = phone_numbers,
        thumb = thumb,
        image = featured_image,
        rating = user_rating.aggregate_rating,
        ratingDescription = user_rating.rating_text,
        locality = location.locality_verbose,
        address = location.address,
        latitude = location.latitude,
        longitude = location.longitude
    )

fun RestaurantReviewsResponse.Companion.ReviewDetailResponse.toRestaurantReview() =
    RestaurantReview(
        id = id,
        reviewText = review_text,
        rating = rating,
        ratingDescription = rating_text,
        userName = user.name,
        userProfileImage = user.profile_image,
        dateDescription = review_time_friendly
    )