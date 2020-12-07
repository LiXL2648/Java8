package com.li.juc.thread;

import java.util.Random;
import java.util.concurrent.*;

public class ScheduledThreadPoolTest {

    public static void main(String[] args) {

        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.schedule(() -> {
                return new Random().nextInt(100);
            }, 1, TimeUnit.SECONDS);

            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }
}
