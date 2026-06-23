package com.github.johnnysc.practicetdd.task002

import com.github.johnnysc.practicetdd.task002.validation.ParserValidation

interface Parser {

    fun parse(raw: String): List<Any>

    class Base(val delimiter: String): Parser {

        init {
            if (delimiter.isEmpty())
                throw IllegalStateException()
        }

        private val parserValidation = ParserValidation.Base()

        override fun parse(raw: String): List<Any> {
            if (raw.isEmpty() || raw.isBlank())
                return emptyList()

            return raw.split(delimiter).mapNotNull { item ->
                parserValidation.validate(value = item)
            }
        }
    }
}