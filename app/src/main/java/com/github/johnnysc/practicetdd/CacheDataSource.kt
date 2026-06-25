package com.github.johnnysc.practicetdd

interface CacheDataSource {

    fun add(item: SimpleData)
    fun data(): List<SimpleData>

    class Timed(
        private val now: Now,
        private val lifeTimeMillis: Long
    ): CacheDataSource {

        private val cacheListOfSimpleData = mutableMapOf<Long, SimpleData>()

        override fun add(item: SimpleData) {
            cacheListOfSimpleData[now.now()] = item
        }

        override fun data(): List<SimpleData> {
            return cacheListOfSimpleData.filterKeys { key ->
                now.now() - key < lifeTimeMillis
            }.values.toList()
        }
    }
}