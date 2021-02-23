package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.service.model

import androidx.annotation.Keep

@Keep
data class CategoriesRestaurantsResponse(
    val categories: List<CategoriesResponse>
) {
    companion object {
        data class CategoriesResponse(
            val categories: CategoryResponse
        )

        data class CategoryResponse(
            val id: Int,
            val name: String
        )
    }
}

