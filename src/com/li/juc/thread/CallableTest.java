package com.li.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) {
        CallableThread callable = new CallableThread();

        FutureTask<Long> task = new FutureTask<>(callable);

        new Thread(task).start();

        try {
            Object result = task.get();
            System.out.println(result);
            System.out.println("结果执行完毕");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CallableThread implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = 0; i <= 100; i++) {
            System.out.println(i);
            sum += i;
        }
        return sum;
    }
}
