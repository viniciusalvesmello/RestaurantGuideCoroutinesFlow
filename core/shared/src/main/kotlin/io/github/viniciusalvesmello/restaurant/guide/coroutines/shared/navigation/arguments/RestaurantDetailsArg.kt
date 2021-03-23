package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.navigation.arguments

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class RestaurantDetailsArg(
    val id: Int,
    val name: String,
    val cuisines: String,
    val phoneNumbers: String,
    val image: String,
    val rating: String,
    val ratingDescription: String,
    val address: String
) : Parcelable