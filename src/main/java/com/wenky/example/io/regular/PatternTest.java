package com.wenky.example.io.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-30 18:22
 */
public class PatternTest {

    /** []表示一个字符 */
    public static void matchesTest() {
        String regex = "[Yy]es|[Yy]|[Tt]rue";
        System.out.println(Pattern.matches(regex, "yes"));
        System.out.println(Pattern.matches(regex, "Yes"));
        System.out.println(Pattern.matches(regex, "Y"));
        System.out.println(Pattern.matches(regex, "y"));
        System.out.println(Pattern.matches(regex, "True"));
        System.out.println(Pattern.matches(regex, "true"));
        System.out.println("yes".matches(regex));
    }

    public static Pattern compile() {
        Pattern pattern = Pattern.compile("[A-Z][a-zA-Z]*");
        return pattern;
    }

    public static void main(String[] args) {
        //        matchesTest();
        System.out.println(splitAppointInterval("aaaafefefbbbb"));
        //        System.out.println(replaceAllDisNumber("  11weq1aswe*%.223"));

        //    System.out.println(matchesEmail("a"));
        //    System.out.println(matchesEmail("a@-.s"));
        //    System.out.println(matchesEmail("a@gmail.com"));
    }

    public static String replaceAllDisNumber(String str) {
        return null == str ? null : str.replaceAll("[^\\d]", "");
    }

    public static String splitAppointInterval(String str) {
        // .*? 懒惰匹配，匹配最短的
        // .* 贪婪匹配匹配最长的
        String regex = "(.*aaa)(.*?)(bbb)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.start(2));
            System.out.println(matcher.end(2));
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            return matcher.group(2);
        }
        return null;
    }

    public static boolean matchesEmail(String email) {
        String regexEmail = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
        return Pattern.matches(regexEmail, email);
    }
}
