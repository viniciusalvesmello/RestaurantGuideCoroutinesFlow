package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.mapper

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.arguments.RestaurantDetailsArg

fun Restaurant.toRestaurantDetailsArg() = RestaurantDetailsArg(
    id = id,
    name = name,
    cuisines = cuisines,
    phoneNumbers = phoneNumbers,
    image = image,
    rating = rating,
    ratingDescription = ratingDescription,
    address = address
)