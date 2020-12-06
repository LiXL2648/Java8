package com.li.juc.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCAlternate {

    public static void main(String[] args) {
        Alternate alternate = new Alternate();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternate.loopA();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternate.loopB();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                alternate.loopC();
            }
        }).start();
    }
}

class Alternate {

    private int num = 1;

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void loopA() {
        lock.lock();

        try {
            if (num != 1) {
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                }
            }

            System.out.print("A");
            num = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void loopB() {
        lock.lock();

        try {
            if (num != 2) {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                }
            }

            System.out.print("B");
            num = 3;
            conditionC.signal();
        } finally {
            lock.unlock();
        }
    }

    public void loopC() {
        lock.lock();

        try {
            if (num != 3) {
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                }
            }

            System.out.print("C");
            num = 1;
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }
}
