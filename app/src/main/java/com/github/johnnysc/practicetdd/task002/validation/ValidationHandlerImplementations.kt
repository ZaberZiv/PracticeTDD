package com.github.johnnysc.practicetdd.task002.validation

class BooleanValidationHandler: ValidationHandler.Base<Boolean>() {
    override fun validate(value: Any): Boolean? {
        return value.toString().toBooleanStrictOrNull()
    }
}

class ByteValidationHandler: ValidationHandler.Base<Byte>() {
    override fun validate(value: Any): Byte? {
        return value.toString().toByteOrNull()
    }
}

class ShortValidationHandler: ValidationHandler.Base<Short>() {
    override fun validate(value: Any): Short? {
        val short = value.toString().toShortOrNull() ?: return null
        return if (short in Byte.MIN_VALUE..Byte.MAX_VALUE) null else short
    }
}

class IntValidationHandler: ValidationHandler.Base<Int>() {
    override fun validate(value: Any): Int? {
        val integer = value.toString().toIntOrNull()
        return if (integer in Short.MIN_VALUE..Short.MAX_VALUE) null else integer
    }
}

class LongValidationHandler: ValidationHandler.Base<Long>() {
    override fun validate(value: Any): Long? {
        val long = value.toString().toLongOrNull()
        return if (long in Int.MIN_VALUE.toLong()..Int.MAX_VALUE.toLong()) null else long
    }
}

class FloatValidationHandler: ValidationHandler.Base<Float>() {
    override fun validate(value: Any): Float? {
        val text = value.toString()

        if (text.toLongOrNull() != null) return null
        val float = text.toFloatOrNull()?.takeIf { it.isFinite() } ?: return null
        if (text.endsWith("f") || text.endsWith("F")) return float

        return float.takeIf { text.valueAfterDot().length <= 1 }
    }
}

class DoubleValidationHandler: ValidationHandler.Base<Double>() {
    override fun validate(value: Any): Double? {
        val text = value.toString()

        if (text.endsWith("f") || text.endsWith("F")) return null
        val double = text.toDoubleOrNull() ?: return null
        val float = text.toFloatOrNull()

        return double.takeIf { float?.isInfinite() == true || text.valueAfterDot().length > 1 }
    }
}

class CharValidationHandler: ValidationHandler.Base<Char>() {
    override fun validate(value: Any): Char? {
        return value.toString().singleOrNull()
    }
}

class StringValidationHandler: ValidationHandler.Base<String>() {
    override fun validate(value: Any): String? {
        val text = value.toString()

        if (text == "null") return null
        if (text.singleOrNull() != null) return null
        if (text.toBooleanStrictOrNull() != null) return null
        if (text.toDoubleOrNull() != null) return null
        if (text.dropLast(1).toFloatOrNull() != null && text.endsWith("f", ignoreCase = true)) return null

        return text
    }
}

class NullValidationHandler: ValidationHandler.Base<Any?>() {
    override fun validate(value: Any): Any? {
        return if (value.toString() == "null") "null" else null
    }
}

fun String.valueAfterDot(): String {
    return substringAfter('.', missingDelimiterValue = "")
}