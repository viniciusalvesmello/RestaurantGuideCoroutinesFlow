package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import androidx.lifecycle.MutableLiveData
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import kotlinx.coroutines.flow.Flow

suspend fun <T> MutableLiveData<ResourceResponse<T>>.postFlow(flow: Flow<T>) {
    this.postValue(
        flow.asResourceResponse()
    )
}