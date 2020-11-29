package com.li.java8.interfact;

public interface SameMethodInterface {

    default String hello() {
        return "Hello LiXL";
    }

    public static String say() {
        return "hello";
    }
}
