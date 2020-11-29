package com.li.java8.forkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class TestForkJoinTask {

    @Test
    public void testForkJoinTask() {

        Instant startTime = Instant.now();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask forkJoinTask = new ForkJoinTask(0L, 50000000000L);

        Long sum = forkJoinPool.invoke(forkJoinTask);

        System.out.println(sum);

        Instant endTime = Instant.now();
        System.out.println(Duration.between(startTime, endTime).toMillis()); // 277 5808 35519
    }

    @Test
    public void testCompute() {

        Instant startTime = Instant.now();

        long sum = 0;

        for (long i = 0; i <= 50000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant endTime = Instant.now();
        System.out.println(Duration.between(startTime, endTime).toMillis()); // 103 5593 22333
    }

    @Test
    public void testparallel() {

        Instant startTime = Instant.now();

        long sum = LongStream.rangeClosed(0, 50000000000L)
                .parallel()
                .reduce(0, Long::sum);

        System.out.println(sum);

        Instant endTime = Instant.now();
        System.out.println(Duration.between(startTime, endTime).toMillis()); // 18555
    }
}
