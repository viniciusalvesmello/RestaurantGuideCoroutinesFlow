package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import kotlinx.android.synthetic.main.row_restaurant_review.view.*

class RestaurantDetailsReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(restaurantReview: RestaurantReview) {
        if (restaurantReview.userProfileImage.isNotEmpty())
            Picasso.get()
                .load(restaurantReview.userProfileImage)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .resize(100, 100)
                .into(itemView.civRestaurantReviewProfile)
        val ratingText = "${restaurantReview.rating} - ${restaurantReview.ratingDescription}"
        itemView.tvRestaurantReviewRating.text = ratingText
        itemView.tvRestaurantReviewDate.text = restaurantReview.dateDescription
        itemView.tvRestaurantReviewUserName.text = restaurantReview.userName
        itemView.tvRestaurantReview.text = restaurantReview.reviewText
    }
}