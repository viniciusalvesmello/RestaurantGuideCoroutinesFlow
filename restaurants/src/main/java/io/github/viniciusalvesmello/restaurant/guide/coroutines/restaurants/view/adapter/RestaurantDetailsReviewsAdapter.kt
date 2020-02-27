package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.inflate

class RestaurantDetailsReviewsAdapter(
    private val listRestaurantReviews: List<RestaurantReview>
) : RecyclerView.Adapter<RestaurantDetailsReviewViewHolder>() {

    override fun getItemViewType(position: Int): Int = R.layout.row_restaurant_review

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantDetailsReviewViewHolder =
        RestaurantDetailsReviewViewHolder(parent.inflate(viewType))

    override fun getItemCount(): Int = listRestaurantReviews.size

    override fun onBindViewHolder(holder: RestaurantDetailsReviewViewHolder, position: Int) =
        holder.bind(listRestaurantReviews[position])
}