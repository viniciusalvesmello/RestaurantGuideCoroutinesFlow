package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.databinding.RowCityBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City

class CitiesViewHolder(
    private val binding: RowCityBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(city: City, onClick: (view: View, city: City) -> Unit) = with(binding) {
        Picasso.get()
            .load(city.imageUrl)
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .into(ivCity)
        tvCityName.text = city.name
        cvCity.setOnClickListener {
            onClick(it, city)
        }
    }
}