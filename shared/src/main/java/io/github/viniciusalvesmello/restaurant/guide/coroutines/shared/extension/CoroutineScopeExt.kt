package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CoroutineScope.launchMain(block: suspend () -> Unit) = launch(Dispatchers.Main) { block() }

fun CoroutineScope.launchDefault(block: suspend () -> Unit) =
    launch(Dispatchers.Default) { block() }

fun CoroutineScope.launchIO(block: suspend () -> Unit) = launch(Dispatchers.IO) { block() }