package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.databinding.RowCityBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City

class CitiesAdapter(
    private val listCities: List<City>,
    private val onClick: (view: View, city: City) -> Unit
) : RecyclerView.Adapter<CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder =
        CitiesViewHolder(
            RowCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listCities.count()

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) =
        holder.bind(listCities[position], onClick)


}