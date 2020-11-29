package com.li.java8.dateTimeApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class DateFormateTest {

    public static void main(String[] args) throws Exception {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                // sdf.parse("2020-11-24")

                return DateFormateThreadLocal.convert("2020-11-24");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futures.add(pool.submit(task));
        }

        for (Future<Date> future : futures) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
