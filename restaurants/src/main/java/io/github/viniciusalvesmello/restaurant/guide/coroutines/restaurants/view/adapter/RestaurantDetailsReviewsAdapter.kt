package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RowRestaurantReviewBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview

class RestaurantDetailsReviewsAdapter(
    private val listRestaurantReviews: List<RestaurantReview>
) : RecyclerView.Adapter<RestaurantDetailsReviewViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantDetailsReviewViewHolder =
        RestaurantDetailsReviewViewHolder(
            RowRestaurantReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listRestaurantReviews.size

    override fun onBindViewHolder(holder: RestaurantDetailsReviewViewHolder, position: Int) =
        holder.bind(listRestaurantReviews[position])
}