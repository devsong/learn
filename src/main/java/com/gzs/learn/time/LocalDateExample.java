package com.gzs.learn.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;

public class LocalDateExample {
    @Test
    public void testLocalDate() {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out
                .println(LocalDate.parse("2016/07/08", DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        LocalTime time = LocalTime.now();
        System.out.println(time);
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void testDateTime() {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        localDateTime = localDateTime.plus(1, ChronoUnit.DAYS);
        System.out.println(localDateTime);

        Instant instant = Instant.now();
        System.out.println(instant.toEpochMilli());
        System.out.println(Date.from(instant));
    }
}
