package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val filters: List<GoodFilter>,
    private val products: List<Good>,
    private val communication: Communication<List<Good>>,
    private val filterCommunication: Communication<List<GoodFilter>>
): ViewModel() {

    private val selectedFilters = mutableListOf<GoodFilter.Abstract>()

    fun change(filter: GoodFilter.Abstract) {
        if (selectedFilters.contains(filter)) {
            selectedFilters.remove(filter)
        } else {
            selectedFilters.add(filter)
        }

        val filteredProducts = products.filter {
            it.map(
                object : Good.Mapper<Boolean> {
                    override fun map(
                        ram: Int,
                        os: OS,
                        displaySize: Double,
                        processor: ProcessorType,
                        price: Double
                    ): Boolean {
                        return selectedFilters.all { selectedFilter ->
                            selectedFilter.map(ram, os, displaySize, processor, price)
                        }
                    }
                }
            )
        }
        communication.map(filteredProducts)
    }
}