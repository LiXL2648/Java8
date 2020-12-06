package com.li.juc.volatileTest;

import java.util.concurrent.atomic.*;

public class AtomicTest {

    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable {

    // private volatile int seriaNum = 0;

    private AtomicInteger seriaNum = new AtomicInteger();


    public int getSeriaNum() {

        return seriaNum.getAndIncrement();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        System.out.println(Thread.currentThread().getName() + ": " + getSeriaNum());
    }
}
