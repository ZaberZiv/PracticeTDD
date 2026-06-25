package com.github.johnnysc.practicetdd.range

interface RangeLimits {

    fun pair(number: Int): RangePair

    class Base(
        private val list: List<Int>
    ): RangeLimits {
        override fun pair(number: Int): RangePair {
            var left: Int = Int.MIN_VALUE
            var right: Int = Int.MAX_VALUE

            for (i in list) {
                if (i < number)
                    left = i

                if (i > number) {
                    right = i
                    break
                }
            }

            return RangePair(left = left, right = right)
        }
    }
}