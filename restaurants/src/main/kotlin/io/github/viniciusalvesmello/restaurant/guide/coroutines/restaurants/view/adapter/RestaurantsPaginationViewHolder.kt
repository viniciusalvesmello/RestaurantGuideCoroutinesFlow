package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RowFooterRecycleViewRestaurantsBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone

class RestaurantsPaginationViewHolder(
    private val binding: RowFooterRecycleViewRestaurantsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(count: Int, onClickLastPage : () -> Unit, onClickNextPage : () -> Unit) = with(binding) {
        btRestaurantsLastPage.setOnClickListener {
            onClickLastPage()
        }
        btRestaurantsNextPage.setOnClickListener {
            onClickNextPage()
        }

        if(count == 0) {
            btRestaurantsLastPage.gone()
            btRestaurantsNextPage.gone()
        }
    }
}