package com.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Observes a [LiveData] until the `block` is done executing.
 */
fun <T> LiveData<T>.observeForTesting(block: (T?) -> Unit) {
    val observer = Observer<T> { }
    try {
        observeForever(observer)
        block(getValueForTesting())
    } finally {
        removeObserver(observer)
    }
}

/**
 * Safely handles observables from LiveData for testing.
 */
@Throws(InterruptedException::class)
fun <T> LiveData<T>.getValueForTesting(): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            removeObserver(this)
        }
    }
    observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    return data
}