package com.gzs.learn.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.Test;

public class DateTest {

    @Test
    public void testDate() {
        // LocalDate now = LocalDate.now();
        // System.out.println(now);
        // System.out.println(now.isLeapYear());
        // System.out.println(now.atTime(OffsetTime.of(1, 1, 1, 0, ZoneOffset.UTC)));
        //
        // LocalDate specifyDate = LocalDate.of(2016, Month.OCTOBER, 9);
        // System.out.println(specifyDate);
        // System.out.println(specifyDate.isLeapYear());
        //
        // System.out.println(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
        //
        // Instant instant = Instant.now();
        // Date date = Date.from(instant);
        // System.out.println(date);

        Date date = Date.from(Instant.from(LocalDateTime.now()));

        System.out.println(date);
    }
}
