package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter.CitiesAdapter
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.listener.CityListener
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.viewmodel.CitiesViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.observe
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.visible
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ViewModelFactory
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import kotlinx.android.synthetic.main.cities_fragment.*
import javax.inject.Inject

class CitiesFragment : DaggerFragment(), CityListener {

    @Inject
    lateinit var appNavigation: AppNavigation

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CitiesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CitiesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cities_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initView()
    }

    private fun initView() {
        viewModel.getCities()
    }

    private fun initObserver() = with(viewModel.viewState) {
        observe(showLoading) {
            handleProgressBar(it)
        }

        observe(cities) {
            handleCities(it)
        }
    }

    private fun handleProgressBar(showLoading: Boolean) {
        if (showLoading) {
            pbCities.visible()
        } else {
            pbCities.gone()
        }
    }

    private fun handleCities(cities: List<City>) {
        rvCities.adapter = CitiesAdapter(cities, this)
        rvCities.layoutManager = LinearLayoutManager(context)
    }

    override fun onClickItemRecycleView(view: View, city: City) {
        appNavigation.navigateFromCityToRestaurant(view, city.id, city.name)
    }
}
