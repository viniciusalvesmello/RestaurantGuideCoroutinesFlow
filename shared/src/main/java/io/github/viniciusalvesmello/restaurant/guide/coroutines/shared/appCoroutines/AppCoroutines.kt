package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher

interface AppCoroutines {
    fun scope(): CoroutineScope
    fun dispatcherMain(): MainCoroutineDispatcher
    fun dispatcherDefault(): CoroutineDispatcher
    fun dispatcherIO(): CoroutineDispatcher
    fun cancel()
}