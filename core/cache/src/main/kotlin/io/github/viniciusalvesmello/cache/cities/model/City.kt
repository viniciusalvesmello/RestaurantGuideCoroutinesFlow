package io.github.viniciusalvesmello.cache.cities.model

import androidx.annotation.Keep

@Keep
data class City(
    val id: Int,
    val name: String,
    val imageUrl: String,
)
