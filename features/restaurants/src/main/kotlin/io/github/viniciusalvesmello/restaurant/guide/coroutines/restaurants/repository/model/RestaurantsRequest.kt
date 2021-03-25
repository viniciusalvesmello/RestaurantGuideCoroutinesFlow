package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model

data class RestaurantsRequest(
    var cityId: Int = 0,
    var cityName: String = "",
    var categoryId: Int = 0,
)
