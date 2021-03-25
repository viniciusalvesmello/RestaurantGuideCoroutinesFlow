package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.cache.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.databinding.RowCityBinding

class CitiesViewHolder(
    private val binding: RowCityBinding,
    private val listener: Listener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(city: City) = with(binding) {
        Picasso.get()
            .load(city.imageUrl)
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .into(ivCity)
        tvCityName.text = city.name
        cvCity.setOnClickListener {
            listener.onCardClickListener(it, city)
        }
    }

    interface Listener {
        fun onCardClickListener(view: View, city: City)
    }
}