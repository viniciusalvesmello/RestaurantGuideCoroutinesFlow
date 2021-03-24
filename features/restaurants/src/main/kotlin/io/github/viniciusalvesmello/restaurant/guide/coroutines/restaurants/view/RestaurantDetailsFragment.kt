package io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.R
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.databinding.RestaurantDetailsFragmentBinding
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.repository.model.RestaurantReview
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.view.adapter.RestaurantDetailsReviewsAdapter
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.RestaurantReviewsViewModel
import io.github.viniciusalvesmello.restaurant.guide.coroutines.restaurants.viewmodel.viewstate.RestaurantReviewsViewState
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.collectLatest
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.gone
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.handleNetworkError
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension.visible
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.AppNavigation
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.arguments.RestaurantDetailsArg
import javax.inject.Inject

@AndroidEntryPoint
class RestaurantDetailsFragment : Fragment() {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: RestaurantReviewsViewModel by viewModels()

    private val restaurantDetailsArg: RestaurantDetailsArg? by lazy {
        arguments?.getParcelable(ARGUMENTS_KEY_RESTAURANT_DETAILS_ARG) as? RestaurantDetailsArg
    }

    private var _binding: RestaurantDetailsFragmentBinding? = null
    private val binding: RestaurantDetailsFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
        initCollectors()
    }

    private fun initView() {
        restaurantDetailsArg?.let {
            val textRating = "${it.rating} - ${it.ratingDescription}"

            binding.tvRestaurantDetailsTitle.text = it.name
            binding.tvRestaurantDetailsRating.text = textRating
            binding.tvRestaurantDetailsCuisines.text = it.cuisines
            binding.tvRestaurantDetailsPhoneNumbers.text = it.phoneNumbers
            binding.tvRestaurantDetailsAddress.text = it.address

            handleRestarantImage(it.image)

            viewModel.getRestaurantReviews(it.id)
        }
    }

    private fun initListeners() {
        binding.ivRestaurantDetailsBackPressed.setOnClickListener {
            appNavigation.onBackPressed(it)
        }
    }

    private fun initCollectors() {
        collectLatest(viewModel.viewState) { it?.let { handleViewState(it) } }
    }

    private fun handleViewState(viewState: RestaurantReviewsViewState) = when(viewState) {
        is RestaurantReviewsViewState.Loading -> {
            showLoading()
        }
        is RestaurantReviewsViewState.Error -> {
            hideLoading()
            handleError(viewState.throwable)
        }
        is RestaurantReviewsViewState.ListRestaurantReview -> {
            handleRestaurantReview(viewState.list)
        }
    }

    private fun showLoading() = binding.pbRestaurantDetails.visible()

    private fun hideLoading() = binding.pbRestaurantDetails.visible()

    private fun handleRestaurantReview(restaurantReview: List<RestaurantReview>) {
        binding.rvRestaurantDetailsReviews.adapter = RestaurantDetailsReviewsAdapter(restaurantReview)
        binding.rvRestaurantDetailsReviews.layoutManager = LinearLayoutManager(context)
    }

    private fun handleError(throwable: Throwable) {
        val message = throwable.handleNetworkError()
        handleError("${message.first} ${message.second}")
    }

    private fun handleError(error: String) {
        binding.pbRestaurantDetails.gone()
        Snackbar.make(
            binding.clRestaurantDetailsSnackBar,
            error,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun handleRestarantImage(urlImage: String) {
        if (urlImage.isNotEmpty()) {
            Picasso.get()
                .load(urlImage)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(binding.ivRestaurantDetails)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARGUMENTS_KEY_RESTAURANT_DETAILS_ARG = "restaurantDetailsArg"
    }
}