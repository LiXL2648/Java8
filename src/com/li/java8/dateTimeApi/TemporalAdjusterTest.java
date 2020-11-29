package com.li.java8.dateTimeApi;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterTest {

    @Test
    public void testWith() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate with = localDate.with((l) -> {
            LocalDate date = (LocalDate) l;
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return date.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return date.plusDays(2);
            } else {
                return date.plusDays(1);
            }
        });
        System.out.println(with);
    }

    @Test
    public void testTemporalAdjusters() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate nextSATURDAY = localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println(nextSATURDAY);

        LocalDate withDayOfMonth = localDate.withDayOfMonth(10);
        System.out.println(withDayOfMonth);
    }
}
