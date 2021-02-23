package io.github.viniciusalvesmello.restaurant.guide.coroutines.flow.navigation

import android.view.View
import androidx.navigation.findNavController
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.CitiesFragmentDirections
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.RestaurantsFragmentDirections
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.arguments.RestaurantDetailsArg

class AppNavigationImpl : AppNavigation {

    override fun navigateFromCityToRestaurant(view: View, cityId: Int, cityName: String) {
        view.findNavController().navigate(
            CitiesFragmentDirections.restaurantsAction(cityId, cityName)
        )
    }

    override fun navigateFromRestaurantToRestaurantDetails(
        view: View,
        restaurantDetailsArg: RestaurantDetailsArg
    ) {
        view.findNavController().navigate(
            RestaurantsFragmentDirections.restaurantDetailsAction(restaurantDetailsArg)
        )
    }

    override fun onBackPressed(view: View) {
        view.findNavController().popBackStack()
    }
}