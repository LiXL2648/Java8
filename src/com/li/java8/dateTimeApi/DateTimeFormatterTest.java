package com.li.java8.dateTimeApi;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterTest {

    @Test
    public void testFormat() {
        DateTimeFormatter dft = DateTimeFormatter.ISO_DATE;

        LocalDateTime dateTime = LocalDateTime.now();

        String dateStr = dateTime.format(dft);

        System.out.println(dateStr);
    }

    @Test
    public void testOfPattern() {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr = dft.format(LocalDateTime.now());
        System.out.println(dateTimeStr);

        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dft);
        System.out.println(dateTime);
    }
}
