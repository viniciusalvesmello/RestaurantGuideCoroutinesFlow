package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.CategoryRestaurants
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.Restaurant
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.mapper.toRestaurantDetailsArg
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter.RestaurantsAdapter
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.RestaurantsViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.observe
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.visible
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ViewModelFactory
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import kotlinx.android.synthetic.main.fragment_restaurants.*
import javax.inject.Inject

class RestaurantsFragment : DaggerFragment() {

    @Inject
    lateinit var appNavigation: AppNavigation

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RestaurantsViewModel::class.java]
    }

    private lateinit var chipAll: Chip

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_restaurants, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObserver()
        initView()
    }

    private fun initListeners() {
        ivRestaurantsBackPressed.setOnClickListener {
            appNavigation.onBackPressed(it)
        }

        cgRestaurantsCategories.setOnCheckedChangeListener { _, chipId ->
            viewModel.categoryId = if (chipId < 0) {
                chipAll.isChecked = true
                chipAll.id
            } else {
                chipId
            }
            viewModel.startItem = 0
            viewModel.getRestaurants()
        }
    }

    private fun initObserver() = with(viewModel.viewState) {
        observe(showLoading) {
            handleProgressBar(it)
        }

        observe(categories) {
            handleCategories(it)
        }

        observe(restaurants) {
            handleRestaurants(it)
        }

        observe(showError) {
            handleError(it)
        }
    }

    private fun handleProgressBar(showLoading: Boolean) {
        if (showLoading) {
            pbRestaurants.visible()
        } else {
            pbRestaurants.gone()
        }
    }

    private fun handleCategories(categories: List<CategoryRestaurants>) {
        cgRestaurantsCategories.removeAllViews()
        createChipAll()
        categories.forEach {
            cgRestaurantsCategories.addView(
                createChip(it)
            )
        }
    }

    private fun handleRestaurants(restaurants: List<Restaurant>) {
        rvRestaurants.adapter = RestaurantsAdapter(restaurants, { view, restaurant ->
            onClickItemRecycleView(view, restaurant)
        }, {
            onClickLastPageFooterRecycleView()
        }, {
            onClickNextPageFooterRecycleView()
        })
        rvRestaurants.layoutManager = LinearLayoutManager(context)
    }

    private fun handleError(error: String) {
        pbRestaurants.gone()
        Snackbar.make(
            clRestaurantsSnackBar,
            error,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun initView() {
        viewModel.cityId = arguments?.getInt(ARGUMENTS_KEY_CITY_ID) ?: 0
        viewModel.cityName = arguments?.getString(ARGUMENTS_KEY_CITY_NAME) ?: ""

        tvRestaurantsTitleCityName.text = viewModel.cityName

        initCategories()

        viewModel.getRestaurants()
    }

    private fun initCategories() {
        if (viewModel.viewState.categories.value == null) {
            viewModel.getCategoriesRestaurants()
            createChipAll()
        }
    }

    private fun createChipAll() {
        createChip(CategoryRestaurants(id = 0, name = getString(R.string.all))).apply {
            chipAll = this
            cgRestaurantsCategories.addView(this)
        }
    }

    private fun createChip(categoryRestaurantsView: CategoryRestaurants): Chip {
        val chip = Chip(context)
        chip.id = categoryRestaurantsView.id
        chip.text = categoryRestaurantsView.name
        chip.isClickable = true
        chip.isCheckable = true
        chip.isChecked = (viewModel.categoryId == chip.id)
        return chip
    }

    private fun onClickItemRecycleView(view: View, restaurant: Restaurant) {
        appNavigation.navigateFromRestaurantToRestaurantDetails(
            view,
            restaurant.toRestaurantDetailsArg()
        )
    }

    private fun onClickLastPageFooterRecycleView() {
        viewModel.getRestaurantsLastPage()
    }

    private fun onClickNextPageFooterRecycleView() {
        viewModel.getRestaurantsNextPage()
    }

    companion object {
        private const val ARGUMENTS_KEY_CITY_ID = "cityId"
        private const val ARGUMENTS_KEY_CITY_NAME = "cityName"
    }
}