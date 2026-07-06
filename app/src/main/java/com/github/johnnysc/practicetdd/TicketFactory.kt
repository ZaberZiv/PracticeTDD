package com.github.johnnysc.practicetdd

interface TicketFactory {

    fun ticket(number: Int): LotteryTicket

    class Base : TicketFactory {
        override fun ticket(number: Int): LotteryTicket {
            return LotteryTicket.Base(number = number)
        }
    }
}