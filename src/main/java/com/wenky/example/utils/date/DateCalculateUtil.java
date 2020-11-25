package com.wenky.example.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-12 15:07
 */
public class DateCalculateUtil {
  // 加上指定天数
  public static Date addDays(Date date, Integer amount) {
    return DateUtils.addDays(date, amount);
  }
  // 减去指定天数
  public static Date reduceDays(Date date, Integer amount) {
    return addDays(date, -amount);
  }

  // 计算时间差
  public static Integer getDifferenceDays(Date start, Date end) {
    return Math.toIntExact(ChronoUnit.DAYS.between(start.toInstant(), end.toInstant()));
  }

  // 计算过完今天还剩多少分钟
  public static Integer getDifferenceMinutes() {
    Date now = new Date();
    LocalDate localDate = DateTransformUtil.date2LocalDate(now);
    System.out.println(DateFormatUtil.RFC3339Time(DateTransformUtil.localDate2Date(localDate)));
    LocalDateTime localDateTime = DateTransformUtil.getEndOfDay(localDate);
    System.out.println(
        DateFormatUtil.RFC3339Time(DateTransformUtil.localDateTime2DateTime(localDateTime)));
    Date endOfDay = DateTransformUtil.localDateTime2DateTime(localDateTime);
    return Math.toIntExact(ChronoUnit.HOURS.between(now.toInstant(), endOfDay.toInstant()));
  }

  public static void main(String[] args) {
    System.out.println(getDifferenceMinutes());

    System.out.println(getDifferenceDays(new Date(), addDays(new Date(), 10)));
  }
}
