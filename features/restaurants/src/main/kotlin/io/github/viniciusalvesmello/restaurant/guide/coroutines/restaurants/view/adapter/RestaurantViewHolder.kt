package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RowRestaurantBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant

class RestaurantViewHolder(
    private val binding: RowRestaurantBinding,
    private val listener: Listener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(restaurant: Restaurant) = with(binding) {
        if (restaurant.thumb.isNotEmpty())
            Picasso.get()
                .load(restaurant.thumb)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(ivRestaurant)
        val ratingText = "${restaurant.rating} - ${restaurant.ratingDescription}"
        tvRestaurantRating.text = ratingText
        tvRestaurantName.text = restaurant.name
        tvRestaurantLocality.text = restaurant.locality
        cvRestaurant.setOnClickListener {
            listener.onCardClickListener(it, restaurant)
        }
    }

    interface Listener {
        fun onCardClickListener(view: View, restaurant: Restaurant)
    }
}