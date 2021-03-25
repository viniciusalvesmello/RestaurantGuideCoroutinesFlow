package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import io.github.viniciusalvesmello.cache.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.databinding.RowCityBinding

class CitiesAdapter(
    private val listener: CitiesViewHolder.Listener
) : PagingDataAdapter<City, CitiesViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
        CitiesViewHolder(
            binding = RowCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener = listener
        )

    companion object {
        private val diffCallback
            get() = object : DiffUtil.ItemCallback<City>() {
                override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
                    oldItem == newItem
            }
    }
}