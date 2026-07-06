package com.github.johnnysc.practicetdd

interface NewInt {

    fun isValid(number: Int): Boolean

    class Default: NewInt {
        override fun isValid(number: Int): Boolean {
            return true
        }
    }

    class Positive(
        private val newInt: NewInt = Default()
    ): NewInt {
        override fun isValid(number: Int): Boolean {
            return number > 0 && newInt.isValid(number)
        }
    }

    class Negative: NewInt {
        override fun isValid(number: Int): Boolean {
            return number < 0
        }
    }

    class Odd(
        private val newInt: NewInt = Default()
    ): NewInt {
        override fun isValid(number: Int): Boolean {
            return number % 2 != 0 && newInt.isValid(number)
        }
    }

    class Less(
        private val limit: Int
    ): NewInt {
        override fun isValid(number: Int): Boolean {
            return number < limit
        }
    }
}