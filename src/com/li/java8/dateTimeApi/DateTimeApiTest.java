package com.li.java8.dateTimeApi;

import org.junit.Test;

import java.time.*;

public class DateTimeApiTest {


    @Test
    public void testPeriod() {
        LocalDate date1 = LocalDate.now();

        LocalDate date2 = LocalDate.of(1996, 5, 4);

        Period period = Period.between(date2, date1);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    @Test
    public void testDuration() {
        Instant ins1 = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration);
        System.out.println(duration.toMillis());
    }

    @Test
    public void testInstant() {
        Instant ins = Instant.now();
        System.out.println(ins);
        OffsetDateTime offsetDateTime = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        System.out.println(ins.toEpochMilli());
        System.out.println(Instant.ofEpochSecond(1));
    }

    @Test
    public void testLocalDateTimeGet() {
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonthValue());
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getHour());
        System.out.println(dateTime.getMinute());
        System.out.println(dateTime.getSecond());
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.println(dateTime.getDayOfYear());
    }

    @Test
    public void testLocalDateTimeMinus() {
        LocalDateTime dateTime = LocalDateTime.now().minusHours(1);
        System.out.println(dateTime);
    }

    @Test
    public void testLocalDateTimePlus() {
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(10);
        System.out.println(dateTime);
    }

    @Test
    public void testLocalDateTimeOf() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 11, 24, 23, 40, 00);
        System.out.println(dateTime);
    }

    @Test
    public void testLocalDateTimeNow() {

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }
}
