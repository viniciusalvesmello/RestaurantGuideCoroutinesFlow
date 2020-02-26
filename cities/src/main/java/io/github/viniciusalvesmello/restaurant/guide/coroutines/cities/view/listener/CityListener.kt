package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.listener

import android.view.View
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City

interface CityListener {
    fun onClickItemRecycleView(view: View, city: City)
}