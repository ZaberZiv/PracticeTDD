package com.github.johnnysc.practicetdd

interface Sorting {

    fun sort(list: List<Int>): List<Int>

    class Base(
        private val forOut: For,
        private val forIn: For
    ): Sorting {
        override fun sort(list: List<Int>): List<Int> {
            val result = list.toMutableList()
            val size = result.size
            forOut.repeat(size, 0) { i ->
                var swapped = false
                forIn.repeat(size - 1 - i, 0) { j ->
                    if (result[j] > result[j + 1]) {
                        val temp = result[j]
                        result[j] = result[j + 1]
                        result[j + 1] = temp
                        swapped = true
                    }
                    false
                }
                !swapped
            }
            return result
        }
    }
}