package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository

import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.mapper.toCategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.mapper.toRestaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.mapper.toRestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.asResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.singleEmit
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RestaurantsRepositoryImpl @Inject constructor(
    private val zomatoService: ZomatoService,
    private val coroutineContext: CoroutineContext
) : RestaurantsRepository {

    override fun getCategoriesRestaurants(coroutineScope: CoroutineScope): ResourceResponse<List<CategoryRestaurants>> =
        singleEmit {
            zomatoService.getCategoriesRestaurants().categories.map { response ->
                response.categories.toCategoryRestaurants()
            }
        }.asResourceResponse(coroutineScope = coroutineScope, coroutineContext = coroutineContext)

    override fun getRestaurants(
        coroutineScope: CoroutineScope,
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ): ResourceResponse<List<Restaurant>> =
        singleEmit {
            zomatoService.getRestaurants(
                entityId,
                entityType,
                sort,
                order,
                category,
                count,
                start
            ).restaurants.map { response ->
                response.restaurant.toRestaurant()
            }
        }.asResourceResponse(coroutineScope = coroutineScope, coroutineContext = coroutineContext)

    override fun getRestaurantReviews(
        coroutineScope: CoroutineScope,
        restaurantId: Int,
        count: Int,
        start: Int
    ): ResourceResponse<List<RestaurantReview>> =
        singleEmit {
            zomatoService.getRestaurantReviews(
                restaurantId,
                count,
                start
            ).user_reviews.map { response ->
                response.review.toRestaurantReview()
            }
        }.asResourceResponse(coroutineScope = coroutineScope, coroutineContext = coroutineContext)
}