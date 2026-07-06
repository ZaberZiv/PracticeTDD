package com.github.johnnysc.practicetdd

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

interface DelayResponse {

    suspend fun <T : Any> delayAfter(delayInMillis: Long, block: suspend () -> T): T

    class Base(
        private val now: Now
    ) : DelayResponse {
        override suspend fun <T : Any> delayAfter(
            delayInMillis: Long,
            block: suspend () -> T
        ): T = coroutineScope {
            val result = async { block() }
            delay(delayInMillis.milliseconds)
            result.await()
        }
    }
}