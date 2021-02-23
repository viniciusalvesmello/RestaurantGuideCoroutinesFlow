package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RowRestaurantReviewBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview

class RestaurantDetailsReviewViewHolder(
    private val binding: RowRestaurantReviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(restaurantReview: RestaurantReview) = with(binding) {
        if (restaurantReview.userProfileImage.isNotEmpty())
            Picasso.get()
                .load(restaurantReview.userProfileImage)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .resize(100, 100)
                .into(civRestaurantReviewProfile)
        val ratingText = "${restaurantReview.rating} - ${restaurantReview.ratingDescription}"
        tvRestaurantReviewRating.text = ratingText
        tvRestaurantReviewDate.text = restaurantReview.dateDescription
        tvRestaurantReviewUserName.text = restaurantReview.userName
        tvRestaurantReview.text = restaurantReview.reviewText
    }
}