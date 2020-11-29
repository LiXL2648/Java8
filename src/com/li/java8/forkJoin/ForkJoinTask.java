package com.li.java8.forkJoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTask extends RecursiveTask<Long> {

    private Long start;

    private Long end;

    private static final Long THRESHOL = 10000L;

    public ForkJoinTask(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        long length = end - start;

        if (length <= THRESHOL) {

            long sum = 0;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {

            long middle = (start + end) / 2;

            ForkJoinTask left = new ForkJoinTask(start, middle);
            left.fork();

            ForkJoinTask right = new ForkJoinTask(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
