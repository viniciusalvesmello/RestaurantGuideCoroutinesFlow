package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.listener

import android.view.View
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant

interface RestaurantListener {
    fun onClickItemRecycleView(view: View, restaurant: Restaurant)
}