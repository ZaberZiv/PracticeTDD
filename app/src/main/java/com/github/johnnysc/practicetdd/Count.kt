package com.github.johnnysc.practicetdd

interface Count {

    fun provide(value: String)

    fun click() = Unit

    interface Callback: Count

    class Base(
        private val callback: Callback
    ): Count {
        private var count = 0

        override fun provide(value: String) {

        }

        override fun click() {
            count++
            callback.provide(count.toString())
        }
    }
}