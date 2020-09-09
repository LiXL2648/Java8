package com.li.java8.interfact;

@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);
}
