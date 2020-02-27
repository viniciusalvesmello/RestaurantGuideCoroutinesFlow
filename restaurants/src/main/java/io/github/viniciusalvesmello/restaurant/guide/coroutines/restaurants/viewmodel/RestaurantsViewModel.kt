package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.model.RestaurantsViewState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.SingleLiveEvent
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.StateView
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) : ViewModel() {

    var cityId = 0
    var cityName = ""
    var categoryId = 0
    var startItem = 0

    private var onLoading: MediatorLiveData<Boolean> = MediatorLiveData()
    private var getCategoriesRestaurants: MutableLiveData<ResourceResponse<List<CategoryRestaurants>>> =
        SingleLiveEvent()
    private var getRestaurants: MutableLiveData<ResourceResponse<List<Restaurant>>> =
        SingleLiveEvent()
    private var getRestaurantReviews: MutableLiveData<ResourceResponse<List<RestaurantReview>>> =
        SingleLiveEvent()
    private var onError: MediatorLiveData<String> = MediatorLiveData()

    var viewState = RestaurantsViewState().apply {
        showLoading = onLoading.apply {
            addSource(switchMap(getRestaurants) { it.state }) {
                it?.let {
                    onLoading.value = it == StateView.LOADING
                }
            }
            addSource(switchMap(getRestaurantReviews) { it.state }) {
                it?.let {
                    onLoading.value = it == StateView.LOADING
                }
            }
        }
        categories = switchMap(getCategoriesRestaurants) { it.data }
        restaurants = switchMap(getRestaurants) { it.data }
        restaurantReviews = switchMap(getRestaurantReviews) { it.data }
        showError = onError.apply {
            addSource(switchMap(getRestaurants) { it.message }) {
                it?.let {
                    onError.value = it
                }
            }
            addSource(switchMap(getRestaurantReviews) { it.message }) {
                it?.let {
                    onError.value = it
                }
            }
        }
    }

    fun getCategoriesRestaurants() {
        getCategoriesRestaurants.postValue(restaurantsRepository.getCategoriesRestaurants())
    }

    fun getRestaurants() {
        getRestaurants.postValue(
            restaurantsRepository.getRestaurants(
                entityId = cityId,
                entityType = GET_RESTAURANTS_ENTITY_TYPE,
                sort = GET_RESTAURANTS_SORT,
                order = GET_RESTAURANTS_ORDER,
                category = categoryId,
                count = GET_RESTAURANTS_COUNT,
                start = startItem
            )
        )
    }

    fun getRestaurantsLastPage() {
        startItem -= GET_RESTAURANTS_COUNT
        getRestaurants()
    }

    fun getRestaurantsNextPage() {
        startItem += GET_RESTAURANTS_COUNT
        getRestaurants()
    }

    fun getRestaurantReviews(restaurantId: Int) {
        getRestaurantReviews.postValue(
            restaurantsRepository.getRestaurantReviews(
                restaurantId = restaurantId,
                count = GET_RESTAURANTS_REVIEWS_COUNT,
                start = GET_RESTAURANTS_REVIEWS_START
            )
        )
    }

    companion object {
        private const val GET_RESTAURANTS_ENTITY_TYPE = "city"
        private const val GET_RESTAURANTS_SORT = "rating"
        private const val GET_RESTAURANTS_ORDER = "desc"
        private const val GET_RESTAURANTS_COUNT = 10
        private const val GET_RESTAURANTS_REVIEWS_COUNT = 50
        private const val GET_RESTAURANTS_REVIEWS_START = 0
    }
}