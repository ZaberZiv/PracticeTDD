package com.github.johnnysc.practicetdd

interface LotteryTicket {
    fun isFake(): Boolean
    fun isWinner(): Boolean

    class Base(
        private val number: Int
    ) : LotteryTicket {
        override fun isFake(): Boolean {
            val digits = number.toString().length
            return number <= 9 || digits % 2 != 0 || digits > 8
        }

        override fun isWinner(): Boolean {
            val text = number.toString()
            val half = text.length / 2
            val firstSum = text.take(half).sumOf { it.digitToInt() }
            val secondSum = text.takeLast(half).sumOf { it.digitToInt() }
            return firstSum == secondSum
        }
    }
}
