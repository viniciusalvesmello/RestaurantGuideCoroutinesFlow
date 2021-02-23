package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model

import androidx.annotation.Keep

@Keep
data class City(
    val id: Int = 0,
    val name: String = "",
    val imageUrl : String = ""
)