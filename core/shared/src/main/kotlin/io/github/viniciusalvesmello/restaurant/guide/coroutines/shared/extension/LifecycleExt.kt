package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, androidx.lifecycle.Observer { it?.let { action(it) } })
}