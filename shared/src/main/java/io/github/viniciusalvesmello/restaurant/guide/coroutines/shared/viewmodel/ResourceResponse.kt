package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel

import androidx.lifecycle.LiveData

class ResourceResponse<T>(
    val state: LiveData<StateView>,
    val data: LiveData<T>,
    val message: LiveData<String>,
    val retry: (() -> ResourceResponse<T>)? = null
)