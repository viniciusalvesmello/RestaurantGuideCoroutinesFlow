package io.github.viniciusalvesmello.cache.cities.mapper

import io.github.viniciusalvesmello.cache.cities.model.City
import io.github.viniciusalvesmello.cache.cities.room.CityEntity

fun CityEntity.toCity() = City(id, name, imageUrl)