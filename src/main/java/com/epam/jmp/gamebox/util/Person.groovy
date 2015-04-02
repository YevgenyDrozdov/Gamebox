package com.epam.jmp.gamebox.util

import groovy.transform.ToString

@ToString
class Person {

    String name;
    int age;

    boolean asBoolean() {
        age < 120;
    }

}
