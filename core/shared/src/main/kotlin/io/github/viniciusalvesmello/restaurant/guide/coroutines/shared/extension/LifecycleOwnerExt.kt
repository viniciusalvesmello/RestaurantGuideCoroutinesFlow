package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.collectLatest(
    flow: Flow<T>,
    block: suspend (value: T) -> Unit
): Job = lifecycleScope.launch {
    flow.collectLatest {
        block(it)
    }
}