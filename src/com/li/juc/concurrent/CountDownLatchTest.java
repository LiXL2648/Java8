package com.li.juc.concurrent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        CountDownLatchThread thread = new CountDownLatchThread(countDownLatch);
        LocalDateTime start = LocalDateTime.now();

        for (int i = 0; i < 5; i++) {
            new Thread(thread).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println("消耗的时间为：" + Duration.between(start, end).toMillis());
    }
}

class CountDownLatchThread implements Runnable {

    CountDownLatch countDownLatch;

    public CountDownLatchThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        synchronized (this) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}