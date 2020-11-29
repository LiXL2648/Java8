package com.li.java8.interfact;

public interface DefaultMethodInterface {

    void test();

    default String hello() {
        return "Hello";
    }
}
