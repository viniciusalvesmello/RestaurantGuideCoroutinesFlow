package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.mapper.toRestaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantsRequest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService

class RestaurantsPageSource(
    private val service: ZomatoService,
    private val request: RestaurantsRequest
) : PagingSource<Int, Restaurant>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Restaurant> {
        val page = params.key ?: INITIAL_PAGE
        val start = (page * GET_RESTAURANTS_COUNT) - GET_RESTAURANTS_COUNT

        return try {
            val response = service.getRestaurants(
                entityId = request.cityId,
                entityType = GET_RESTAURANTS_ENTITY_TYPE,
                sort = GET_RESTAURANTS_SORT,
                order = GET_RESTAURANTS_ORDER,
                category = request.categoryId,
                count = GET_RESTAURANTS_COUNT,
                start = start
            )
            val result = response.restaurants.map { it.restaurant.toRestaurant() }
            val prevKey = if (page == INITIAL_PAGE) null else page.dec()
            val nextKey =
                if ((response.results_start + response.results_shown) >= response.results_found.dec()) {
                    null
                } else {
                    page.inc()
                }
            LoadResult.Page(data = result, prevKey = prevKey, nextKey = nextKey)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Restaurant>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.inc()
                ?: state.closestPageToPosition(it)?.nextKey?.dec()
        }

    companion object {
        private const val INITIAL_PAGE = 1
        private const val GET_RESTAURANTS_ENTITY_TYPE = "city"
        private const val GET_RESTAURANTS_SORT = "rating"
        private const val GET_RESTAURANTS_ORDER = "desc"
        private const val GET_RESTAURANTS_COUNT = 10
    }
}