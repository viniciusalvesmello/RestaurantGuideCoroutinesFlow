package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.paging.RestaurantReviewsPageSource
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface RestaurantReviewsRepository {
    fun getRestaurantReviews(id: Int): Flow<PagingData<RestaurantReview>>
}

class RestaurantReviewsRepositoryImpl @AssistedInject constructor(
    private val service: ZomatoService,
    @Assisted private val viewModelScope: CoroutineScope
) : RestaurantReviewsRepository {

    override fun getRestaurantReviews(id: Int): Flow<PagingData<RestaurantReview>> =
        Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                initialLoadSize = INITIAL_LOAD_SIZE
            )
        ) {
            RestaurantReviewsPageSource(service = service, id = id)
        }.flow.cachedIn(viewModelScope)

    @AssistedFactory
    interface RestaurantReviewsRepositoryFactory {
        fun create(viewModelScope: CoroutineScope): RestaurantReviewsRepositoryImpl
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 2
        private const val INITIAL_LOAD_SIZE = 10
    }
}