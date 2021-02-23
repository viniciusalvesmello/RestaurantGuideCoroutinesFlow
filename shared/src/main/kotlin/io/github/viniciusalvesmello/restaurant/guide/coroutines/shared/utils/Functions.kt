package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils

import androidx.lifecycle.MutableLiveData
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.StateView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> singleEmit(block: suspend () -> T): Flow<T> = flow {
    emit(block())
}

fun <T> buildResourceResponse(): Triple<MutableLiveData<T>, MutableLiveData<StateView>, MutableLiveData<String>> {
    return Triple(MutableLiveData(), MutableLiveData(), MutableLiveData())
}