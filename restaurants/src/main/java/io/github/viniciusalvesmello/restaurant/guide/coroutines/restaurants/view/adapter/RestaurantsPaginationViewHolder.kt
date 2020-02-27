package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import kotlinx.android.synthetic.main.row_footer_recycle_view_restaurants.view.*

class RestaurantsPaginationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(count: Int, onClickLastPage : () -> Unit, onClickNextPage : () -> Unit) {
        itemView.btRestaurantsLastPage.setOnClickListener {
            onClickLastPage()
        }
        itemView.btRestaurantsNextPage.setOnClickListener {
            onClickNextPage()
        }

        if(count == 0) {
            itemView.btRestaurantsLastPage.gone()
            itemView.btRestaurantsNextPage.gone()
        }
    }
}