package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RowRestaurantReviewBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview

class RestaurantDetailsReviewsAdapter(
) : PagingDataAdapter<RestaurantReview, RestaurantDetailsReviewViewHolder>(diffUtilCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantDetailsReviewViewHolder = RestaurantDetailsReviewViewHolder(
        RowRestaurantReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RestaurantDetailsReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffUtilCallBack get() = object : DiffUtil.ItemCallback<RestaurantReview>() {
            override fun areItemsTheSame(
                oldItem: RestaurantReview,
                newItem: RestaurantReview
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RestaurantReview,
                newItem: RestaurantReview
            ): Boolean = oldItem == newItem
        }
    }
}