package com.li.juc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) {

        ThreadDemo thread = new ThreadDemo();
        CallableDemo callableDemo = new CallableDemo();

        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //pool.submit(thread);
            Future<Integer> future = pool.submit(callableDemo);
            futures.add(future);
        }

        try {
            for (Future<Integer> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }
}

class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        int sum = 0;

        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}

class ThreadDemo implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

