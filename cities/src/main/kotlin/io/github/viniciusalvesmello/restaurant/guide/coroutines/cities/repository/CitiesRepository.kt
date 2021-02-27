package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.repository

import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import kotlinx.coroutines.CoroutineScope

interface CitiesRepository {
    fun getCities(coroutineScope: CoroutineScope): ResourceResponse<List<City>>
}