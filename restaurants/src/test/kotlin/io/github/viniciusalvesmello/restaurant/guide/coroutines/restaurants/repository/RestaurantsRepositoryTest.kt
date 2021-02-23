package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.ZomatoService
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.mapper.toCategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.mapper.toRestaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.mapper.toRestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.CategoriesRestaurantsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.RestaurantReviewsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model.RestaurantsResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.test
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.StateView
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class RestaurantsRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val testScope = TestCoroutineScope()
    private val service = mockk<ZomatoService>()
    private val getCategoriesRestaurants: CategoriesRestaurantsResponse = mockk(relaxed = true)
    private val getRestaurants: RestaurantsResponse = mockk(relaxed = true)
    private val getRestaurantReviews: RestaurantReviewsResponse = mockk(relaxed = true)

    private val repository: RestaurantsRepository = RestaurantsRepositoryImpl(
        zomatoService = service,
        coroutineContext = Dispatchers.Unconfined
    )

    private val entityId: Int = randomInt()
    private val entityType: String = randomString()
    private val sort: String = randomString()
    private val order: String = randomString()
    private val category: Int = randomInt()
    private val count: Int = randomInt()
    private val start: Int = randomInt()
    private val restaurantId: Int = randomInt()

    @After
    fun cleanUp() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun whenGetCategoriesRestaurantsReturnSuccessLiveDataShouldReturnSuccess() {

        coEvery {
            service.getCategoriesRestaurants()
        } returns getCategoriesRestaurants

        val request = repository.getCategoriesRestaurants(testScope)

        val data = getCategoriesRestaurants.categories.map { response ->
            response.categories.toCategoryRestaurants()
        }

        request.data.test().assertValue(data)
        request.state.test().assertValue(StateView.SUCCESS)
        request.message.test().assertNotInvoked()
    }

    @Test
    fun whenGetCategoriesRestaurantsReturnErrorLiveDataShouldReturnError() {

        coEvery {
            service.getCategoriesRestaurants()
        }.throws(Throwable())

        val request = repository.getCategoriesRestaurants(testScope)

        request.data.test().assertNotInvoked()
        request.state.test().assertValue(StateView.ERROR)
        request.message.test().assertValue(
            "Aconteceu algo inesperado! Houve um erro ao tentar realizar a operação"
        )
    }

    @Test
    fun whenGetRestaurantsRestaurantsReturnSuccessLiveDataShouldReturnSuccess() {

        coEvery {
            service.getRestaurants(entityId, entityType, sort, order, category, count, start)
        } returns getRestaurants

        val request =
            repository.getRestaurants(testScope, entityId, entityType, sort, order, category, count, start)

        val data = getRestaurants.restaurants.map { response ->
            response.restaurant.toRestaurant()
        }

        request.data.test().assertValue(data)
        request.state.test().assertValue(StateView.SUCCESS)
        request.message.test().assertNotInvoked()
    }

    @Test
    fun whenGetRestaurantsReturnErrorLiveDataShouldReturnError() {

        coEvery {
            service.getRestaurants(entityId, entityType, sort, order, category, count, start)
        }.throws(Throwable())

        val request =
            repository.getRestaurants(testScope, entityId, entityType, sort, order, category, count, start)

        request.data.test().assertNotInvoked()
        request.state.test().assertValue(StateView.ERROR)
        request.message.test().assertValue(
            "Aconteceu algo inesperado! Houve um erro ao tentar realizar a operação"
        )
    }

    @Test
    fun whenGetRestaurantReviewsRestaurantsReturnSuccessLiveDataShouldReturnSuccess() {

        coEvery {
            service.getRestaurantReviews(restaurantId, count, start)
        } returns getRestaurantReviews

        val request = repository.getRestaurantReviews(testScope, restaurantId, count, start)

        val data = getRestaurantReviews.user_reviews.map { response ->
            response.review.toRestaurantReview()
        }

        request.data.test().assertValue(data)
        request.state.test().assertValue(StateView.SUCCESS)
        request.message.test().assertNotInvoked()
    }

    @Test
    fun whenGetRestaurantReviewsReturnErrorLiveDataShouldReturnError() {

        coEvery {
            service.getRestaurantReviews(restaurantId, count, start)
        }.throws(Throwable())

        val request = repository.getRestaurantReviews(testScope, restaurantId, count, start)

        request.data.test().assertNotInvoked()
        request.state.test().assertValue(StateView.ERROR)
        request.message.test().assertValue(
            "Aconteceu algo inesperado! Houve um erro ao tentar realizar a operação"
        )
    }

    private fun randomString(): String = java.util.UUID.randomUUID().toString()
    private fun randomInt(): Int = (0..100).random()
}