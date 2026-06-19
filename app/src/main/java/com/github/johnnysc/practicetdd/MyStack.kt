package com.github.johnnysc.practicetdd

interface MyStack<T : Any> {

    fun pop(): T

    fun push(item: T)

    abstract class Base<T : Any>(maxCount: Int) : MyStack<T> {
        init {
            if (maxCount <= 0)
                throw IllegalStateException()
        }

        protected val stack = ArrayDeque<T>(maxCount)

        override fun pop(): T {
            assertStackIsNotEmpty()
            return stack.removeLast()
        }

        protected fun assertStackIsNotEmpty(message: String = "") {
            if (stack.isEmpty())
                throw IllegalStateException(message)
        }
    }

    class LIFO<T : Any>(val maxCount: Int) : Base<T>(maxCount = maxCount) {
        override fun push(item: T) {
            if (stack.size == maxCount)
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            stack.addLast(item)
        }
    }

    class FIFO<T : Any>(val maxCount: Int) : Base<T>(maxCount = maxCount) {
        override fun push(item: T) {
            if (stack.size == maxCount)
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            stack.addFirst(item)
        }
    }
}
