package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter.RestaurantDetailsReviewsAdapter
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.RestaurantsViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.observe
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.visible
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.injection.ViewModelFactory
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.arguments.RestaurantDetailsArg
import kotlinx.android.synthetic.main.fragment_restaurant_details.*
import javax.inject.Inject

class RestaurantDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var appNavigation: AppNavigation

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RestaurantsViewModel::class.java]
    }

    private val restaurantDetailsArg: RestaurantDetailsArg? by lazy {
        arguments?.getParcelable(ARGUMENTS_KEY_RESTAURANT_DETAILS_ARG) as? RestaurantDetailsArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_restaurant_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObserver()
        initView()
    }

    private fun initListeners() {
        ivRestaurantDetailsBackPressed.setOnClickListener {
            appNavigation.onBackPressed(it)
        }
    }

    private fun initObserver() = with(viewModel.viewState) {
        observe(showLoading) {
            handleProgressBar(it)
        }

        observe(restaurantReviews) {
            handleRestaurantReview(it)
        }

        observe(showError) {
            handleError(it)
        }
    }

    private fun handleProgressBar(showLoading: Boolean) {
        if (showLoading) {
            pbRestaurantDetails.visible()
        } else {
            pbRestaurantDetails.gone()
        }
    }

    private fun handleRestaurantReview(restaurantReview: List<RestaurantReview>) {
        rvRestaurantDetailsReviews.adapter = RestaurantDetailsReviewsAdapter(restaurantReview)
        rvRestaurantDetailsReviews.layoutManager = LinearLayoutManager(context)
    }

    private fun handleError(error: String) {
        pbRestaurantDetails.gone()
        Snackbar.make(
            clRestaurantDetailsSnackBar,
            error,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun initView() {
        restaurantDetailsArg?.let {
            val textRating = "${it.rating} - ${it.ratingDescription}"

            tvRestaurantDetailsTitle.text = it.name
            tvRestaurantDetailsRating.text = textRating
            tvRestaurantDetailsCuisines.text = it.cuisines
            tvRestaurantDetailsPhoneNumbers.text = it.phoneNumbers
            tvRestaurantDetailsAddress.text = it.address

            handleRestarantImage(it.image)

            viewModel.getRestaurantReviews(it.id)
        }
    }

    private fun handleRestarantImage(urlImage: String) {
        if (urlImage.isNotEmpty()) {
            Picasso.get()
                .load(urlImage)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(ivRestaurantDetails)
        }
    }

    companion object {
        private const val ARGUMENTS_KEY_RESTAURANT_DETAILS_ARG = "restaurantDetailsArg"
    }
}