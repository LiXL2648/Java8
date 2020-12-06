package com.li.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        new Thread(() -> readWriteLockDemo.write(26), "Write-1").start();

        new Thread(() -> readWriteLockDemo.write(48), "Write-2").start();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> readWriteLockDemo.read(), "Read-" + i).start();
        }
    }
}
class ReadWriteLockDemo {

    private int num = 0;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + ": " + num);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(int num) {
        lock.writeLock().lock();

        try {
            this.num = num;
            System.out.println(Thread.currentThread().getName() + ": " + this.num);
        } finally {
            lock.writeLock().unlock();
        }
    }
}

