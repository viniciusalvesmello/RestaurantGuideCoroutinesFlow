package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

fun String?.handle(default: String = "") = this ?: default