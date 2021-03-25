package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

fun Int?.handle(default: Int = 0) = this ?: default