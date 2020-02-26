package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.listener.RestaurantListener
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import kotlinx.android.synthetic.main.row_restaurant.view.*

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(restaurant: Restaurant, restaurantListener: RestaurantListener) {
        if (restaurant.thumb.isNotEmpty())
            Picasso.get()
                .load(restaurant.thumb)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(itemView.ivRestaurant)
        val ratingText = "${restaurant.rating} - ${restaurant.ratingDescription}"
        itemView.tvRestaurantRating.text = ratingText
        itemView.tvRestaurantName.text = restaurant.name
        itemView.tvRestaurantLocality.text = restaurant.locality
        itemView.cvRestaurant.setOnClickListener {
            restaurantListener.onClickItemRecycleView(it, restaurant)
        }
    }
}