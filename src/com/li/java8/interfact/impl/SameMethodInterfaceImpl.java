package com.li.java8.interfact.impl;

import com.li.java8.interfact.DefaultMethodInterface;
import com.li.java8.interfact.SameMethodInterface;

public class SameMethodInterfaceImpl implements DefaultMethodInterface, SameMethodInterface {

    @Override
    public void test() {
        SameMethodInterfaceImpl sameMethodInterface = new SameMethodInterfaceImpl();
        System.out.println(sameMethodInterface.hello());
    }

    @Override
    public String hello() {
        return SameMethodInterface.super.hello();
    }

    public static void main(String[] args) {
        SameMethodInterfaceImpl sameMethodInterface = new SameMethodInterfaceImpl();
        sameMethodInterface.test();
        System.out.println(SameMethodInterface.say());
    }
}
