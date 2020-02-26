package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation

import android.view.View
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.arguments.RestaurantDetailsArg

interface AppNavigation {
    fun navigateFromCityToRestaurant(view: View, cityId: Int, cityName: String)
    fun navigateFromRestaurantToRestaurantDetails(view: View, restaurantDetailsArg: RestaurantDetailsArg)
    fun onBackPressed(view: View)
}