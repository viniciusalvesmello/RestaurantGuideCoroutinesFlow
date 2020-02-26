package io.github.viniciusalvesmello.restaurant.guide.coroutines.shared.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.test() = TestObserver(this)

class TestObserver<T>(target: LiveData<T>) {
    private val values = mutableListOf<T>()
    private val fakeObserver = Observer<T> {
        values += it
    }

    init {
        target.observeForever(fakeObserver)
    }

    fun assertValue(value: T) {
        assert(value == values.last())
    }

    fun assertValue(value: T, numInvocations: Int = 1) {
        assert(numInvocations == values.size)
        assert(value == values.last())
    }

    fun assertNotInvoked() {
        assert(0 == values.size)
    }

    fun assertInvoked(numInvocations: Int = 1) {
        assert(numInvocations == values.size)
    }

    fun inOrder(block: InOrder<T>.() -> Unit) {
        InOrder(values).block()
    }
}

class InOrder<T>(private val values: List<T>) {
    private var position = 0

    fun verify(value: T) {
        val expected = values[position++]
        assert(expected == value)
    }
}