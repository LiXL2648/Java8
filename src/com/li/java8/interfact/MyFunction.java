package com.li.java8.interfact;

public interface MyFunction<T, R> {

    R operator(T ... t);
}
