package com.gzs.learn.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;

public class DateTimeTest {
    static final String ZONE_DARWIN = "Australia/Darwin";

    static final String ZONE_CHONGQING = "Asia/Chongqing";

    static final String ZONE_HARBIN = "Asia/Harbin";

    @Test
    public void testDate() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.isLeapYear());
        System.out.println(now.atTime(OffsetTime.of(1, 1, 1, 0, ZoneOffset.UTC)));

        LocalDate specifyDate = LocalDate.of(2016, Month.OCTOBER, 9);
        System.out.println(specifyDate);
        System.out.println(specifyDate.isLeapYear());
        System.out.println(010);
        System.out.println(0x12);

        one: for (int i = 0; i < 10; i++) {
            System.out.println(i);
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                if (i == 3 && j == 3) {
                    break one;
                }
            }
        }
    }

    @Test
    public void testLocalTime() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        localTime = LocalTime.now(ZoneId.of(ZONE_DARWIN));
        System.out.println(localTime);

        localTime = LocalTime.of(11, 20);
        System.out.println(localTime);
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        localDateTime = LocalDateTime.now(ZoneId.of(ZONE_DARWIN));
        System.out.println(localDateTime);
    }

    @Test
    public void testInstant() {
        Instant instant = Instant.now();
        System.out.println(instant.getLong(ChronoField.MILLI_OF_SECOND));
        System.out.println(instant.getLong(ChronoField.INSTANT_SECONDS));
        System.out.println(System.currentTimeMillis());
        // System.out.println(Date.from(instant));
        long start = System.nanoTime();
        long current = LocalDateTime.now().toInstant(ZoneOffset.of("+08:00")).toEpochMilli();
        long cost = System.nanoTime() - start;
        System.out.println(current + " cost:" + cost);
        System.out.println(System.currentTimeMillis());
    }
    
    @Test
    public void testDateConvertLocalDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    public void testLocalDateConvertDate() {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.format(date));
    }

    @Test
    public void testDateConvertDateTime() {
        Date date = new Date();
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime);
        localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of(ZONE_DARWIN));
        System.out.println(localDateTime);
    }

    @Test
    public void testDateTimeConvertDate() {
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.toInstant(ZoneOffset.of("+08:00")));
        DateFormat df = new SimpleDateFormat(pattern);
        System.out.println(df.format(date));
    }

    @Test
    public void testZoneDateTime() {
        System.out.println("current time:" + ZonedDateTime.now());
        System.out.println("current time for timezone:" + ZONE_CHONGQING
                + ZonedDateTime.now(ZoneId.of(ZONE_CHONGQING)));
        System.out.println("current time for timezone:" + ZONE_HARBIN
                + ZonedDateTime.now(ZoneId.of(ZONE_HARBIN)));
        System.out.println("current time for timezone:" + ZONE_DARWIN + " "
                + ZonedDateTime.now(ZoneId.of(ZONE_DARWIN)).toInstant().toEpochMilli());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testTemporal() {
        LocalDateTime temporal = LocalDateTime.now();
        System.out.println(temporal.get(ChronoField.HOUR_OF_DAY));
        System.out.println(temporal.truncatedTo(ChronoUnit.MINUTES));
        System.out.println(temporal.adjustInto(LocalDateTime.of(2017, 10, 14, 18, 10, 0)));
    }

    @Test
    public void testFormatDatetime() {
        Date date = new Date();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(
                dtf.format(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())));
    }
}
