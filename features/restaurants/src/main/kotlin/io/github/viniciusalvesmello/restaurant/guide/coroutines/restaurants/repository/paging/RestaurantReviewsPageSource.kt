package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.mapper.toRestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService

class RestaurantReviewsPageSource(
    private val service: ZomatoService,
    private val id: Int
) : PagingSource<Int, RestaurantReview>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RestaurantReview> {
        val page = params.key ?: INITIAL_PAGE
        val start = (page * GET_RESTAURANTS_REVIEWS_COUNT) - GET_RESTAURANTS_REVIEWS_COUNT

        return try {
            val response = service.getRestaurantReviews(
                id = id,
                count = GET_RESTAURANTS_REVIEWS_COUNT,
                start = start
            )
            val result = response.user_reviews.map { it.review.toRestaurantReview() }
            val prevKey = if (page == INITIAL_PAGE) null else page.dec()
            val nextKey =
                if ((response.reviews_start + response.reviews_shown) >= response.reviews_count.dec()) {
                    null
                } else {
                    page.inc()
                }
            LoadResult.Page(data = result, prevKey = prevKey, nextKey = nextKey)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RestaurantReview>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.inc()
                ?: state.closestPageToPosition(it)?.nextKey?.dec()
        }

    companion object {
        private const val INITIAL_PAGE = 1
        private const val GET_RESTAURANTS_REVIEWS_COUNT = 10
    }
}