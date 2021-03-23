package io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import io.github.viniciusalvesmello.cache.cities.CitiesCacheViewModel
import io.github.viniciusalvesmello.cache.cities.model.City
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.databinding.CitiesFragmentBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter.CitiesAdapter
import io.github.viniciusalvesmello.restaurant.guide.coroutines.cities.view.adapter.CitiesViewHolder
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.collectLatest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.visible
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import kotlinx.coroutines.flow.distinctUntilChangedBy
import javax.inject.Inject

@AndroidEntryPoint
class CitiesFragment : Fragment(), CitiesViewHolder.Listener {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: CitiesCacheViewModel by viewModels()

    private var _binding: CitiesFragmentBinding? = null
    private val binding: CitiesFragmentBinding get() = _binding!!
    private val listAdapter: CitiesAdapter by lazy {
        CitiesAdapter(this)
    }

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

        initView()
        initCollectors()
    }

    private fun initView() {
        binding.rvCities.adapter = listAdapter
    }

    private fun initCollectors() {
        collectLatest(viewModel.citiesPagingData) { listAdapter.submitData(it) }
        collectLatest(listAdapter.loadStateFlow.distinctUntilChangedBy { it.refresh }) { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    binding.pbCities.visible()
                }
                is LoadState.NotLoading -> {
                    binding.pbCities.gone()
                }
                is LoadState.Error -> {
                    binding.pbCities.gone()
                    (loadState.refresh as? LoadState.Error)?.error?.let {
                        Log.d("ERROR", it.toString())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCardClickListener(view: View, city: City) {
        appNavigation.navigateFromCityToRestaurant(view, city.id, city.name)
    }
}
