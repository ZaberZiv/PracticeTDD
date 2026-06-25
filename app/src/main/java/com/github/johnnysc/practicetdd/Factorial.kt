package com.github.johnnysc.practicetdd

interface Factorial<T : Number> {

    fun value(number: T): T

    class Int : Factorial<kotlin.Int> {
        override fun value(number: kotlin.Int): kotlin.Int {
            if (number < 0)
                throw IllegalArgumentException()

            var factorial = 1

            for (i in 1..number) {
                factorial *= i
            }

            return factorial
        }
    }

    class Double : Factorial<kotlin.Double> {
        override fun value(number: kotlin.Double): kotlin.Double {
            if (number < 0)
                throw IllegalArgumentException()

            var factorial = 1.0
            var current = number

            while (current > 1.0) {
                factorial *= current
                current -= 1.0
            }

            return factorial
        }
    }

    class BigInteger : Factorial<java.math.BigInteger> {
        override fun value(number: java.math.BigInteger): java.math.BigInteger {
            if (number < java.math.BigInteger.ZERO)
                throw IllegalArgumentException()

            var factorial = java.math.BigInteger.ONE
            var current = number

            while (current > java.math.BigInteger.ONE) {
                factorial = factorial.multiply(current)
                current = current.subtract(java.math.BigInteger.ONE)
            }

            return factorial
        }
    }

    class Factory(
        private val intVal: Factorial<kotlin.Int>,
        private val doubleVal: Factorial<kotlin.Double>,
        private val bigInt: Factorial<java.math.BigInteger>
    )  {
        fun value(number: kotlin.Int): Number {
            if (number < 0)
                throw IllegalArgumentException()

            return when (number) {
                in 0..12 -> intVal.value(number)
                in 13..170 -> doubleVal.value(number.toDouble())
                in 171..11000 -> bigInt.value(java.math.BigInteger(number.toString()))
                else -> throw IllegalStateException()
            }
        }
    }
}