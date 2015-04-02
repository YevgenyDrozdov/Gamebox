package com.epam.jmp.gamebox.util

import groovy.transform.ToString

@ToString
class Money {

    long amount;
    String currency;

    Money plus(int m) {
        return new Money(amount: amount + m, currency: currency)
    }

    Money plus(Money m) {
        return new Money(amount: amount + m.amount, currency: currency)
    }

}


