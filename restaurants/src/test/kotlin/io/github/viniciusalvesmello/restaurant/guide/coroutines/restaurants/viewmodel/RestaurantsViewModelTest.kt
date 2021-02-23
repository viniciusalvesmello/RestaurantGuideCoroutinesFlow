package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.RestaurantsRepository
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.test
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.buildResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.StateView
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RestaurantsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val repository: RestaurantsRepository = mockk()
    private lateinit var viewModel: RestaurantsViewModel

    private val listCategoryRestaurants: List<CategoryRestaurants> = mockk(relaxed = true)
    private val listRestaurant: List<Restaurant> = mockk(relaxed = true)
    private val listRestaurantReview: List<RestaurantReview> = mockk(relaxed = true)

    private val restaurantId: Int = randomInt()

    @Before
    fun setUp() {
        viewModel = RestaurantsViewModel(repository)
    }

    @Test
    fun whenGetCategoriesRestaurantsShouldReturnList() {

        mockGetCategoriesRestaurantsSuccess()

        viewModel.getCategoriesRestaurants()

        viewModel.viewState.apply {
            showLoading.test().assertNotInvoked()
            categories.test().assertValue(listCategoryRestaurants)
            restaurants.test().assertNotInvoked()
            restaurantReviews.test().assertNotInvoked()
            showError.test().assertNotInvoked()
        }
    }

    @Test
    fun whenGetRestaurantsShouldReturnList() {

        mockGetRestaurantsSuccess()

        viewModel.getRestaurants()

        viewModel.viewState.apply {
            showLoading.test().assertValue(false)
            categories.test().assertNotInvoked()
            restaurants.test().assertValue(listRestaurant)
            restaurantReviews.test().assertNotInvoked()
            showError.test().assertNotInvoked()
        }
    }

    @Test
    fun whenGetRestaurantsShouldReturnError() {

        mockGetRestaurantsError()

        viewModel.getRestaurants()

        viewModel.viewState.apply {
            showLoading.test().assertValue(false)
            categories.test().assertNotInvoked()
            restaurants.test().assertNotInvoked()
            restaurantReviews.test().assertNotInvoked()
            showError.test().assertValue("Ocorreu um erro")
        }
    }

    @Test
    fun whenGetRestaurantsShouldReturnLoading() {

        mockGetRestaurantsLoading()

        viewModel.getRestaurants()

        viewModel.viewState.apply {
            showLoading.test().assertValue(true)
            categories.test().assertNotInvoked()
            restaurants.test().assertNotInvoked()
            restaurantReviews.test().assertNotInvoked()
            showError.test().assertNotInvoked()
        }
    }

    @Test
    fun whenGetRestaurantReviewsShouldReturnList() {

        mockGetRestaurantReviewsSuccess()

        viewModel.getRestaurantReviews(restaurantId)

        viewModel.viewState.apply {
            showLoading.test().assertValue(false)
            categories.test().assertNotInvoked()
            restaurants.test().assertNotInvoked()
            restaurantReviews.test().assertValue(listRestaurantReview)
            showError.test().assertNotInvoked()
        }
    }

    @Test
    fun whenGetRestaurantReviewsShouldReturnError() {

        mockGetRestaurantReviewsError()

        viewModel.getRestaurantReviews(restaurantId)

        viewModel.viewState.apply {
            showLoading.test().assertValue(false)
            categories.test().assertNotInvoked()
            restaurants.test().assertNotInvoked()
            restaurantReviews.test().assertNotInvoked()
            showError.test().assertValue("Ocorreu um erro")
        }
    }

    @Test
    fun whenGetRestaurantReviewsShouldReturnLoading() {
        mockGetRestaurantReviewsLoading()

        viewModel.getRestaurantReviews(restaurantId)

        viewModel.viewState.apply {
            showLoading.test().assertValue(true)
            categories.test().assertNotInvoked()
            restaurants.test().assertNotInvoked()
            restaurantReviews.test().assertNotInvoked()
            showError.test().assertNotInvoked()
        }
    }

    private fun mockGetCategoriesRestaurantsSuccess() {

        val (data, state, message) = buildResourceResponse<List<CategoryRestaurants>>()
        data.value = listCategoryRestaurants
        state.value = StateView.SUCCESS

        every {
            repository.getCategoriesRestaurants(coroutineScope = viewModel.viewModelScope)
        } returns ResourceResponse<List<CategoryRestaurants>>(state, data, message)
    }

    private fun mockGetRestaurantsSuccess() {

        val (data, state, message) = buildResourceResponse<List<Restaurant>>()
        data.value = listRestaurant
        state.value = StateView.SUCCESS

        every {
            repository.getRestaurants(
                coroutineScope = viewModel.viewModelScope,
                entityId = viewModel.cityId,
                entityType = GET_RESTAURANTS_ENTITY_TYPE,
                sort = GET_RESTAURANTS_SORT,
                order = GET_RESTAURANTS_ORDER,
                category = viewModel.categoryId,
                count = GET_RESTAURANTS_COUNT,
                start = viewModel.startItem
            )
        } returns ResourceResponse<List<Restaurant>>(state, data, message)
    }

    private fun mockGetRestaurantsError() {

        val (data, state, message) = buildResourceResponse<List<Restaurant>>()
        state.value = StateView.ERROR
        message.value = "Ocorreu um erro"

        every {
            repository.getRestaurants(
                coroutineScope = viewModel.viewModelScope,
                entityId = viewModel.cityId,
                entityType = GET_RESTAURANTS_ENTITY_TYPE,
                sort = GET_RESTAURANTS_SORT,
                order = GET_RESTAURANTS_ORDER,
                category = viewModel.categoryId,
                count = GET_RESTAURANTS_COUNT,
                start = viewModel.startItem
            )
        } returns ResourceResponse<List<Restaurant>>(state, data, message)
    }

    private fun mockGetRestaurantsLoading() {

        val (data, state, message) = buildResourceResponse<List<Restaurant>>()
        state.value = StateView.LOADING

        every {
            repository.getRestaurants(
                coroutineScope = viewModel.viewModelScope,
                entityId = viewModel.cityId,
                entityType = GET_RESTAURANTS_ENTITY_TYPE,
                sort = GET_RESTAURANTS_SORT,
                order = GET_RESTAURANTS_ORDER,
                category = viewModel.categoryId,
                count = GET_RESTAURANTS_COUNT,
                start = viewModel.startItem
            )
        } returns ResourceResponse<List<Restaurant>>(state, data, message)
    }

    private fun mockGetRestaurantReviewsSuccess() {

        val (data, state, message) = buildResourceResponse<List<RestaurantReview>>()
        data.value = listRestaurantReview
        state.value = StateView.SUCCESS

        every {
            repository.getRestaurantReviews(
                coroutineScope = viewModel.viewModelScope,
                restaurantId = restaurantId,
                count = GET_RESTAURANTS_REVIEWS_COUNT,
                start = GET_RESTAURANTS_REVIEWS_START
            )
        } returns ResourceResponse<List<RestaurantReview>>(state, data, message)
    }

    private fun mockGetRestaurantReviewsError() {

        val (data, state, message) = buildResourceResponse<List<RestaurantReview>>()
        state.value = StateView.ERROR
        message.value = "Ocorreu um erro"

        every {
            repository.getRestaurantReviews(
                coroutineScope = viewModel.viewModelScope,
                restaurantId = restaurantId,
                count = GET_RESTAURANTS_REVIEWS_COUNT,
                start = GET_RESTAURANTS_REVIEWS_START
            )
        } returns ResourceResponse<List<RestaurantReview>>(state, data, message)
    }

    private fun mockGetRestaurantReviewsLoading() {

        val (data, state, message) = buildResourceResponse<List<RestaurantReview>>()
        state.value = StateView.LOADING

        every {
            repository.getRestaurantReviews(
                coroutineScope = viewModel.viewModelScope,
                restaurantId = restaurantId,
                count = GET_RESTAURANTS_REVIEWS_COUNT,
                start = GET_RESTAURANTS_REVIEWS_START
            )
        } returns ResourceResponse<List<RestaurantReview>>(state, data, message)
    }

    private fun randomInt(): Int = (0..100).random()

    companion object {
        private const val GET_RESTAURANTS_ENTITY_TYPE = "city"
        private const val GET_RESTAURANTS_SORT = "rating"
        private const val GET_RESTAURANTS_ORDER = "desc"
        private const val GET_RESTAURANTS_COUNT = 10
        private const val GET_RESTAURANTS_REVIEWS_COUNT = 50
        private const val GET_RESTAURANTS_REVIEWS_START = 0
    }
}