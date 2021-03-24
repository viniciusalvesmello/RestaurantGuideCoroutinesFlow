package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CoroutineScope.launchIO(
    error: suspend (throwable: Throwable) -> Unit = {},
    success: suspend () -> Unit,
) = launch(Dispatchers.IO) {
    try {
        success()
    } catch (throwable: Throwable) {
        error(throwable)
    }
}