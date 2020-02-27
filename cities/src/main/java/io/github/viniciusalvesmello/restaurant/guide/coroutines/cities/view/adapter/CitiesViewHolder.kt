package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City
import kotlinx.android.synthetic.main.row_city.view.*

class CitiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(city: City, onClick: (view: View, city: City) -> Unit) {
            Picasso.get()
                .load(city.imageUrl)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(itemView.image_view_city)
            itemView.text_view_city_name.text = city.name
            itemView.card_view_city.setOnClickListener {
                onClick(it, city)
            }
        }
    }