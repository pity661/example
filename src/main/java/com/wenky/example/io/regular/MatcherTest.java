package com.wenky.example.io.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-30 18:58
 */
public class MatcherTest {
    public static void common() {
        String regex = "([Tt])hanks";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("Thanks, thanks very much");
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            if (matcher.group(1).equals("T")) {
                matcher.appendReplacement(sb, "Thank you");
            } else {
                matcher.appendReplacement(sb, "thank you");
            }
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

    public static void common1() {
        String regex = "([Tt])hanks";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("Thanks, thanks very much");
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "$1hank you");
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

    public static void common2() {
        String value = "Thanks, thanks very much";
        System.out.println(value.replaceAll("([Tt]hanks)", "$1 you"));
        System.out.println(value.replaceAll("([Tt])hanks", "$1hank you"));
    }

    public static void common3() {
        String regex = "([Tt])hanks";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("Thanks, thanks very much");
        System.out.println(matcher.replaceAll("$1hank you"));
    }

    public static void main(String[] args) {
        //        common();
        //        common1();
        //        common2();
        //        common3();
        //        System.out.println(underlineToCamel("ae_wqw_eq"));
        underlineToCamelCommon();
    }

    // 无法实现
    public static String underlineToCamel(String value) {
        return value.replaceAll("([_])([a-z])", "$2");
    }

    public static void underlineToCamelCommon() {
        String regex = "[_]([a-z])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("ae_wqw_eq");
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
    }

    /**
     * 判断一个字符串是否都是数字
     *
     * @param str
     * @return
     */
    public static boolean pureNumberCheck(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches("^\\d*$");
    }
}
