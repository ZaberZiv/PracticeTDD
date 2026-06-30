package com.github.johnnysc.practicetdd

interface Good {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(
            ram: Int,
            os: OS,
            displaySize: Double,
            processor: ProcessorType,
            price: Double
        ): T
    }
}
