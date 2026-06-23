package com.github.johnnysc.practicetdd.task002.validation

interface ParserValidation {
    fun validate(value: Any): Any?

    class Base : ParserValidation {
        override fun validate(value: Any): Any? {
            val validation = BooleanValidationHandler()
            // order doesn't matter
            validation
                .setNext(ByteValidationHandler())
                .setNext(ShortValidationHandler())
                .setNext(IntValidationHandler())
                .setNext(LongValidationHandler())
                .setNext(FloatValidationHandler())
                .setNext(DoubleValidationHandler())
                .setNext(CharValidationHandler())
                .setNext(StringValidationHandler())
                .setNext(NullValidationHandler())

            return validation.handle(value)
        }
    }
}