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
import java.time.temporal.ChronoField;
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
        System.out.println(Date.from(instant));
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
        System.out.println(ZonedDateTime.now());
        System.out.println(ZonedDateTime.now(ZoneId.of(ZONE_CHONGQING)));
        System.out.println(ZonedDateTime.now(ZoneId.of(ZONE_HARBIN)));
        System.out.println(ZonedDateTime.now(ZoneId.of(ZONE_DARWIN)).toInstant().toEpochMilli());
        System.out.println(System.currentTimeMillis());
    }
}
