package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository

import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun getCities(): ResourceResponse<List<City>>
    fun dispose()
}