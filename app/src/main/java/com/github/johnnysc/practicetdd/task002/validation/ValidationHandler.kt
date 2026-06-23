package com.github.johnnysc.practicetdd.task002.validation

interface ValidationHandler {
    fun setNext(handler: ValidationHandler): ValidationHandler

    fun handle(value: Any): Any?

    abstract class Base<T> : ValidationHandler {
        private var next: ValidationHandler? = null

        override fun setNext(handler: ValidationHandler): ValidationHandler {
            next = handler
            return handler
        }

        override fun handle(value: Any): Any? {
            return validate(value) ?: next?.handle(value)
        }

        protected abstract fun validate(value: Any): T?
    }
}