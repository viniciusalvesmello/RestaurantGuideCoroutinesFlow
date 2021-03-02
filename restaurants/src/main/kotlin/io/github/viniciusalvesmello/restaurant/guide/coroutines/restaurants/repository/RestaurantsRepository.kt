package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.mapper.toCategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.mapper.toRestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantsRequest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.paging.RestaurantsPageSource
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.asResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.singleEmit
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface RestaurantsRepository {

    fun getCategoriesRestaurants(
        coroutineScope: CoroutineScope
    ): ResourceResponse<List<CategoryRestaurants>>

    fun getRestaurants(
        request: RestaurantsRequest,
        viewModelScope: CoroutineScope
    ): Flow<PagingData<Restaurant>>

    fun getRestaurantReviews(
        coroutineScope: CoroutineScope,
        restaurantId: Int,
        count: Int,
        start: Int
    ): ResourceResponse<List<RestaurantReview>>
}

class RestaurantsRepositoryImpl @Inject constructor(
    private val service: ZomatoService,
    private val coroutineContext: CoroutineContext
) : RestaurantsRepository {

    override fun getCategoriesRestaurants(coroutineScope: CoroutineScope): ResourceResponse<List<CategoryRestaurants>> =
        singleEmit {
            service.getCategoriesRestaurants().categories.map { response ->
                response.categories.toCategoryRestaurants()
            }
        }.asResourceResponse(coroutineScope = coroutineScope, coroutineContext = coroutineContext)

    override fun getRestaurants(
        request: RestaurantsRequest,
        viewModelScope: CoroutineScope,
    ): Flow<PagingData<Restaurant>> = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE
        )
    ) {
        RestaurantsPageSource(
            service = service,
            request = request,
        )
    }.flow.cachedIn(viewModelScope)


    override fun getRestaurantReviews(
        coroutineScope: CoroutineScope,
        restaurantId: Int,
        count: Int,
        start: Int
    ): ResourceResponse<List<RestaurantReview>> =
        singleEmit {
            service.getRestaurantReviews(
                restaurantId,
                count,
                start
            ).user_reviews.map { response ->
                response.review.toRestaurantReview()
            }
        }.asResourceResponse(coroutineScope = coroutineScope, coroutineContext = coroutineContext)

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 2
        private const val INITIAL_LOAD_SIZE = 10
    }
}