package com.github.johnnysc.practicetdd

interface Validation {

    fun isValid(text: String): Result

    class Password(
        private val minLength: Int = 1,
        private val upperCaseLettersCount: Int = 0,
        private val lowerCaseLettersCount: Int = 0,
        private val numbersCount: Int = 0,
        private val specialSignsCount: Int = 0
    ) : Validation {

        init {
            if (minLength <= 0 || upperCaseLettersCount < 0
                || lowerCaseLettersCount < 0 || numbersCount < 0 || specialSignsCount < 0
            ) {
                throw IllegalStateException()
            }
        }

        private val specialSigns = listOf("!", "@", "$", "#", "*", " ")

        override fun isValid(text: String): Result {

            if (text.isEmpty() || text.length < minLength)
                return Result.MinLengthInsufficient(minLength = minLength)

            if (upperCaseLettersCount > 0 && !text.any { it.isUpperCase() })
                return Result.UpperCaseLettersCountInsufficient(upperCaseLettersCount = upperCaseLettersCount)

            if (upperCaseLettersCount > 0 && text.count { it.isUpperCase() } < upperCaseLettersCount)
                return Result.UpperCaseLettersCountInsufficient(upperCaseLettersCount = upperCaseLettersCount)

            if (lowerCaseLettersCount > 0 && !text.any { it.isLowerCase() })
                return Result.LowerCaseLettersCountInsufficient(lowerCaseLettersCount = lowerCaseLettersCount)

            if (lowerCaseLettersCount > 0 && text.count { it.isLowerCase() } < lowerCaseLettersCount)
                return Result.LowerCaseLettersCountInsufficient(lowerCaseLettersCount = lowerCaseLettersCount)

            if (numbersCount > 0 && text.count { it.isDigit() } < numbersCount)
                return Result.NumbersCountInsufficient(numbersCount = numbersCount)

            if (specialSignsCount > 0 && text.count { specialSigns.contains(it.toString()) } < specialSignsCount)
                return Result.SpecialSignsInsufficient(specialSignsCount = specialSignsCount)

            return Result.Valid
        }
    }

    sealed class Result {
        object Valid : Result()

        data class MinLengthInsufficient(
            private val minLength: Int
        ) : Result()

        data class UpperCaseLettersCountInsufficient(
            private val upperCaseLettersCount: Int
        ) : Result()

        data class LowerCaseLettersCountInsufficient(
            private val lowerCaseLettersCount: Int
        ) : Result()

        data class NumbersCountInsufficient(
            private val numbersCount: Int
        ) : Result()

        data class SpecialSignsInsufficient(
            private val specialSignsCount: Int
        ) : Result()
    }
}