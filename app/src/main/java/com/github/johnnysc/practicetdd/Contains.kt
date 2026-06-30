package com.github.johnnysc.practicetdd

interface Contains {

    fun contains(
        collection: List<String>,
        item: String
    ): Boolean

    class Base(
        private val value: For
    ): Contains {
        override fun contains(
            collection: List<String>,
            item: String
        ): Boolean {
            var contains = false
            value.repeat(
                max = collection.size,
                start = 0
            ) {
                contains = collection[it] == item
                contains
            }
            return contains
        }
    }
}