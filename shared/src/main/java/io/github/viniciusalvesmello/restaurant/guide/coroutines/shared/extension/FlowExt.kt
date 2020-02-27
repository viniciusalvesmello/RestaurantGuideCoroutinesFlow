package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines.AppCoroutines
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.utils.buildResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.ResourceResponse
import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.viewmodel.StateView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asResourceResponse(
    appCoroutines: AppCoroutines,
    retry: (() -> ResourceResponse<T>)? = null,
    transformer: (T) -> T = { it },
    isEmptyPredicate: (T) -> Boolean = { false },
    sendEmptyData: Boolean = true
): ResourceResponse<T> {
    val (result, state, errorMessage) = buildResourceResponse<T>()

    appCoroutines.launchIO {
        this.onStart {
            state.postValue(StateView.LOADING)
        }.onEach { data ->
            val isEmpty = isEmptyPredicate(data)
            if (sendEmptyData || isEmpty.not()) result.postValue(transformer(data))
            val networkState = if (isEmpty) StateView.EMPTY else StateView.SUCCESS
            state.postValue(networkState)
        }.catch {
            state.postValue(StateView.ERROR)
            val message = it.handleNetworkError()
            errorMessage.postValue("${message.first} ${message.second}")
        }.collect()
    }

    return ResourceResponse(
        data = result,
        state = state,
        message = errorMessage,
        retry = retry
    )
}