package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RestaurantsFragmentBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.mapper.toRestaurantDetailsArg
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter.RestaurantsAdapter
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.RestaurantsViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.viewstate.RestaurantsViewState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.collectLatest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.handle
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.handleNetworkError
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.visible
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import kotlinx.coroutines.flow.distinctUntilChangedBy
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantsFragment : Fragment() {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val listAdapter: RestaurantsAdapter by lazy {
        RestaurantsAdapter(::onClickItemRecycleView)
    }

    private val viewModel: RestaurantsViewModel by viewModels()

    private var _binding: RestaurantsFragmentBinding? = null
    private val binding: RestaurantsFragmentBinding get() = _binding!!

    private lateinit var chipAll: Chip

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAdapter()
        initListeners()
        initCollectors()
    }

    private fun initView() {
        viewModel.request.cityId = arguments?.getInt(ARGUMENTS_KEY_CITY_ID).handle()
        viewModel.request.cityName = arguments?.getString(ARGUMENTS_KEY_CITY_NAME).handle()
        binding.tvRestaurantsTitleCityName.text = viewModel.request.cityName
        viewModel.getCategoriesRestaurants()
        createChipAll()
    }

    private fun initAdapter() {
        binding.rvRestaurants.adapter = listAdapter
    }

    private fun initListeners() {
        binding.ivRestaurantsBackPressed.setOnClickListener {
            appNavigation.onBackPressed(it)
        }

        binding.cgRestaurantsCategories.setOnCheckedChangeListener { _, chipId ->
            viewModel.request.categoryId = if (chipId < 0) {
                chipAll.isChecked = true
                chipAll.id
            } else {
                chipId
            }
            listAdapter.refresh()
        }
    }

    private fun initCollectors() {
        collectLatest(viewModel.viewState) { it?.let { handleViewState(it) } }
        collectLatest(viewModel.pagingData) { listAdapter.submitData(it) }
        collectLatest(listAdapter.loadStateFlow.distinctUntilChangedBy { it.refresh }) { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    showLoading()
                }
                is LoadState.NotLoading -> {
                    hideLoading()
                }
                is LoadState.Error -> {
                    hideLoading()
                    handleError((loadState.refresh as LoadState.Error).error)
                }
            }
        }
    }

    private fun handleViewState(viewState: RestaurantsViewState) = when (viewState) {
        is RestaurantsViewState.ListCategoryRestaurants -> {
            handleCategories(viewState.list)
        }
    }

    private fun handleCategories(categories: List<CategoryRestaurants>) {
        binding.cgRestaurantsCategories.removeAllViews()
        createChipAll()
        categories.forEach {
            binding.cgRestaurantsCategories.addView(
                createChip(it)
            )
        }
    }

    private fun showLoading() = binding.pbRestaurants.visible()

    private fun hideLoading() = binding.pbRestaurants.gone()

    private fun handleError(throwable: Throwable) {
        val message = throwable.handleNetworkError()
        handleError("${message.first} ${message.second}")
    }

    private fun handleError(error: String) {
        binding.pbRestaurants.gone()
        Snackbar.make(
            binding.clRestaurantsSnackBar,
            error,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun createChipAll() {
        createChip(CategoryRestaurants(id = 0, name = getString(R.string.all))).apply {
            chipAll = this
            binding.cgRestaurantsCategories.addView(this)
        }
    }

    private fun createChip(categoryRestaurantsView: CategoryRestaurants): Chip {
        val chip = Chip(context)
        chip.id = categoryRestaurantsView.id
        chip.text = categoryRestaurantsView.name
        chip.isClickable = true
        chip.isCheckable = true
        chip.isChecked = (viewModel.request.categoryId == chip.id)
        return chip
    }

    private fun onClickItemRecycleView(view: View, restaurant: Restaurant) {
        appNavigation.navigateFromRestaurantToRestaurantDetails(
            view,
            restaurant.toRestaurantDetailsArg()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARGUMENTS_KEY_CITY_ID = "cityId"
        private const val ARGUMENTS_KEY_CITY_NAME = "cityName"
    }
}