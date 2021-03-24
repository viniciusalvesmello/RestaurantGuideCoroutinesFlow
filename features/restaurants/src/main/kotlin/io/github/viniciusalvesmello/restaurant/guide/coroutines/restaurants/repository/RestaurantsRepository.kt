package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.mapper.toCategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantsRequest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.paging.RestaurantsPageSource
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepository {
    suspend fun getCategoriesRestaurants(): List<CategoryRestaurants>
    fun getRestaurants(request: RestaurantsRequest): Flow<PagingData<Restaurant>>
}

class RestaurantsRepositoryImpl @AssistedInject constructor(
    private val service: ZomatoService,
    @Assisted private val viewModelScope: CoroutineScope
) : RestaurantsRepository {

    override suspend fun getCategoriesRestaurants(): List<CategoryRestaurants> =
        service.getCategoriesRestaurants().categories.map { it.categories.toCategoryRestaurants() }

    override fun getRestaurants(
        request: RestaurantsRequest,
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

    @AssistedFactory
    interface RestaurantsRepositoryFactory {
        fun create(viewModelScope: CoroutineScope): RestaurantsRepositoryImpl
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val PREFETCH_DISTANCE = 2
        private const val INITIAL_LOAD_SIZE = 10
    }
}