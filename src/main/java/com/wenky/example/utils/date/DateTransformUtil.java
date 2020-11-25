package com.wenky.example.utils.date;

import java.time.*;
import java.time.temporal.*;
import java.util.Date;
import java.util.Locale;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-12 14:45
 */
public class DateTransformUtil {
  public static LocalDate getDateNow() {
    return LocalDate.now();
  }

  public static LocalDateTime getTimeNow() {
    return LocalDateTime.now();
  }

  public static Date localDate2Date(LocalDate localDate) {
    ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
    return Date.from(zonedDateTime.toInstant());
  }
  // 转化为只有日期
  public static Date localDateTime2Date(LocalDateTime localDateTime) {
    return localDate2Date(localDateTime.toLocalDate());
  }
  // 转化带时间
  public static Date localDateTime2DateTime(LocalDateTime localDateTime) {
    ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
    return Date.from(zonedDateTime.toInstant());
  }

  public static LocalDate date2LocalDate(Date date) {
    ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
    return zonedDateTime.toLocalDate();
  }

  public static LocalDateTime date2LocalDateTime(Date date) {
    ZonedDateTime zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());
    return zonedDateTime.toLocalDateTime();
  }

  public static LocalDateTime getStartOfDay(LocalDate date) {
    LocalDateTime time = LocalDateTime.of(date, LocalTime.MIN);
    return time;
  }

  public static LocalDateTime getEndOfDay(LocalDate date) {
    LocalDateTime time = LocalDateTime.of(date, LocalTime.MAX);
    return time;
  }

  public static Date getStartDayOfMonth(LocalDate date) {
    LocalDate now = date.with(TemporalAdjusters.firstDayOfMonth());
    return localDate2Date(now);
  }

  public static Date getEndDayOfMonth(LocalDate date) {
    LocalDate now = date.with(TemporalAdjusters.lastDayOfMonth());
    Date.from(now.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    return localDate2Date(now);
  }

  // TemporalAccessor -> localDate localDateTime
  public static Date getStartDayOfWeek(TemporalAccessor date) {
    TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
    LocalDate localDate = LocalDate.from(date);
    localDate = localDate.with(fieldISO, 1);
    return localDate2Date(localDate);
  }

  public static Date getEndDayOfWeek(TemporalAccessor date) {
    TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
    LocalDate localDate = LocalDate.from(date);
    localDate = localDate.with(fieldISO, 7);
    return Date.from(
        localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
  }

  public static void main(String[] args) {
    String dateLocal = DateFormatUtil.date2String(localDate2Date(LocalDate.now()));
    String dateNow = DateFormatUtil.date2String(new Date());
    System.out.println("dateLocal -> " + dateLocal);
    System.out.println("dateNow -> " + dateNow);
  }
}
