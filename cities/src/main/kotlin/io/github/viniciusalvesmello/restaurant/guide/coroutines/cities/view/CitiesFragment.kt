package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.databinding.CitiesFragmentBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter.CitiesAdapter
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.viewmodel.CitiesViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.observe
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.visible
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import javax.inject.Inject

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: CitiesViewModel by viewModels()

    private var _binding: CitiesFragmentBinding? = null
    private val binding: CitiesFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CitiesFragmentBinding.inflate(inflater, container, false)
        return binding.root
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
            binding.pbCities.visible()
        } else {
            binding.pbCities.gone()
        }
    }

    private fun handleCities(cities: List<City>) {
        binding.rvCities.adapter = CitiesAdapter(cities) { view, city ->
            onClickItemRecycleView(view, city)
        }
        binding.rvCities.layoutManager = LinearLayoutManager(context)
    }

    private fun onClickItemRecycleView(view: View, city: City) {
        appNavigation.navigateFromCityToRestaurant(view, city.id, city.name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
