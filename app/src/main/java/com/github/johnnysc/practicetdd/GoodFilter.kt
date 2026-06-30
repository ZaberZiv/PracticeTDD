package com.github.johnnysc.practicetdd

interface GoodFilter {

    fun map(
        ram: Int,
        os: OS,
        displaySize: Double,
        processor: ProcessorType,
        price: Double
    ): Boolean

    abstract class Abstract: GoodFilter
}