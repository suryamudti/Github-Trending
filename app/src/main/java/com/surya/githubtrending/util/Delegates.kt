package com.surya.githubtrending.util

import kotlinx.coroutines.*

/**
 * Created by suryamudti on 13/11/2019.
 */

fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}