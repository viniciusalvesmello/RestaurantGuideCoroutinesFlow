package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines.AppCoroutines
import kotlinx.coroutines.launch

fun AppCoroutines.launchMain(block: suspend () -> Unit) =
    this.scope().launch(this.dispatcherMain()) {
        block()
    }

fun AppCoroutines.launchDefault(block: suspend () -> Unit) =
    this.scope().launch(this.dispatcherDefault()) {
        block()
    }

fun AppCoroutines.launchIO(block: suspend () -> Unit) =
    this.scope().launch(this.dispatcherIO()) {
        block()
    }