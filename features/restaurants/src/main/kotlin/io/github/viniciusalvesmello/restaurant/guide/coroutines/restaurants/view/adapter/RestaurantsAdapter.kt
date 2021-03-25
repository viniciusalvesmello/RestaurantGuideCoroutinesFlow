package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RowRestaurantBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant

class RestaurantsAdapter(
    private val listener: RestaurantViewHolder.Listener
) : PagingDataAdapter<Restaurant, RestaurantViewHolder>(diffUtilCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(
            binding = RowRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener = listener
        )

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffUtilCallBack get() = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean =
                oldItem == newItem
        }
    }
}