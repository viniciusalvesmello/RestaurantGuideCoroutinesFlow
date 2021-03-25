package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantReviewsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantReviewsRepositoryImpl.RestaurantReviewsRepositoryFactory
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RestaurantReviewsViewModel @Inject constructor(
    private val repositoryFactory: RestaurantReviewsRepositoryFactory
) : ViewModel() {

    private val repository: RestaurantReviewsRepository by lazy {
        repositoryFactory.create(viewModelScope)
    }

    fun getPagingData(id: Int): Flow<PagingData<RestaurantReview>> =
        repository.getRestaurantReviews(id)
}