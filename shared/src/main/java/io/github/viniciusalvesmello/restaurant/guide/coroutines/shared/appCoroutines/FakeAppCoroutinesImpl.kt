package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher

class FakeAppCoroutinesImpl : AppCoroutines {

    private val job: Job = Job()
    private val dispatcherUnconfined: CoroutineDispatcher = Dispatchers.Unconfined
    private val dispatcherMain: MainCoroutineDispatcher = Dispatchers.Main
    private val dispatcherDefault: CoroutineDispatcher = dispatcherUnconfined
    private val dispatcherIO: CoroutineDispatcher = dispatcherUnconfined

    override fun scope(): CoroutineScope = CoroutineScope(dispatcherUnconfined + job)

    override fun dispatcherMain(): MainCoroutineDispatcher = dispatcherMain

    override fun dispatcherDefault(): CoroutineDispatcher = dispatcherDefault

    override fun dispatcherIO(): CoroutineDispatcher = dispatcherIO

    override fun cancel() {
        job.cancel()
    }
}