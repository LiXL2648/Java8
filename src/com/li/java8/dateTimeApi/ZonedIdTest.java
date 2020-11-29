package com.li.java8.dateTimeApi;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

public class ZonedIdTest {

    @Test
    public void testDateTransform() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Date 转换为 LocalDateTime
        Date date = new Date();
        System.out.println(sdf.format(date));
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(dateTime.format(dtf));

        // LocalDateTime 转换为 Date
        Date date1 = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(sdf.format(date1));
    }

    @Test
    public void testGetAvailableZoneIds() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.stream()
                .filter(a -> a.contains("America"))
                .forEach(System.out::println);
    }

    @Test
    public void testZoneIdOf() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(dateTime);
    }

    @Test
    public void testZonedDateTime() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime);
    }
}
