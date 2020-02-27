package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.appCoroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher

class AppCoroutinesImpl : AppCoroutines {

    private val job: Job = Job()
    private val dispatcherMain: MainCoroutineDispatcher = Dispatchers.Main
    private val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO

    override fun scope(): CoroutineScope = CoroutineScope(dispatcherMain + job)

    override fun dispatcherMain(): MainCoroutineDispatcher = dispatcherMain

    override fun dispatcherDefault(): CoroutineDispatcher = dispatcherDefault

    override fun dispatcherIO(): CoroutineDispatcher = dispatcherIO

    override fun cancel() {
        job.cancel()
    }
}