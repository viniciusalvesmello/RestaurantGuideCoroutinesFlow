package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.mapper

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.arguments.RestaurantDetailsArg

fun Restaurant.toRestaurantDetailsArg() = RestaurantDetailsArg(
    id,
    name,
    cuisines,
    phoneNumbers,
    image,
    rating,
    ratingDescription,
    address
)