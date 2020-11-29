package com.li.java8.interfact.impl;

import com.li.java8.interfact.DefaultMethodInterface;

    public class DefaultMethodInterfaceImpl extends SameMethodClass implements DefaultMethodInterface {

        @Override
        public void test() {
            DefaultMethodInterfaceImpl defaultMethodInterfaceImpl = new DefaultMethodInterfaceImpl();
            String hello = defaultMethodInterfaceImpl.hello();
            System.out.println(hello);
        }

        public static void main(String[] args) {
            DefaultMethodInterfaceImpl defaultMethodInterface = new DefaultMethodInterfaceImpl();
            defaultMethodInterface.test();
        }
    }
