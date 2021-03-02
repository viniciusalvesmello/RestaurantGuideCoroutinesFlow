package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantsRequest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.state.RestaurantsViewState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.SingleLiveEvent
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.StateView
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    private val repository: RestaurantsRepository
) : ViewModel() {

    val request = RestaurantsRequest()
    val pagingData: Flow<PagingData<Restaurant>>
        get() = repository.getRestaurants(request, viewModelScope)

    private var onLoading: MediatorLiveData<Boolean> = MediatorLiveData()
    private var getCategoriesRestaurants: MutableLiveData<ResourceResponse<List<CategoryRestaurants>>> =
        SingleLiveEvent()
    private var getRestaurantReviews: MutableLiveData<ResourceResponse<List<RestaurantReview>>> =
        SingleLiveEvent()
    private var onError: MediatorLiveData<String> = MediatorLiveData()

    var viewState = RestaurantsViewState().apply {
        showLoading = onLoading.apply {
            addSource(switchMap(getRestaurantReviews) { it.state }) {
                it?.let {
                    onLoading.value = it == StateView.LOADING
                }
            }
        }
        categories = switchMap(getCategoriesRestaurants) { it.data }
        restaurantReviews = switchMap(getRestaurantReviews) { it.data }
        showError = onError.apply {
            addSource(switchMap(getRestaurantReviews) { it.message }) {
                it?.let {
                    onError.value = it
                }
            }
        }
    }

    fun getCategoriesRestaurants() {
        getCategoriesRestaurants.postValue(
            repository.getCategoriesRestaurants(
                coroutineScope = viewModelScope
            )
        )
    }

    fun getRestaurantReviews(restaurantId: Int) {
        getRestaurantReviews.postValue(
            repository.getRestaurantReviews(
                coroutineScope = viewModelScope,
                restaurantId = restaurantId,
                count = GET_RESTAURANTS_REVIEWS_COUNT,
                start = GET_RESTAURANTS_REVIEWS_START
            )
        )
    }

    companion object {
        private const val GET_RESTAURANTS_REVIEWS_COUNT = 50
        private const val GET_RESTAURANTS_REVIEWS_START = 0
    }
}