package com.wenky.example.utils.date;

import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-12 15:04
 */
public class DateFormatUtil {

    private static final String RFC3339_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZ";
    private static final String YYYY_MM_DD_DATE_FORMAT = "yyyy-MM-dd";

    public static Date RFC3339StringToDate(String s) {
        try {
            return StringUtils.isBlank(s) ? null : DateUtils.parseDate(s, RFC3339_DATE_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String RFC3339Time(Date date) {
        return DateFormatUtils.format(date, RFC3339_DATE_FORMAT);
    }

    public static String date2String(Date date) {
        return DateFormatUtils.format(date, YYYY_MM_DD_DATE_FORMAT);
    }

    public static Date string2Date(String s) {
        try {
            return StringUtils.isBlank(s) ? null : DateUtils.parseDate(s, YYYY_MM_DD_DATE_FORMAT);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
