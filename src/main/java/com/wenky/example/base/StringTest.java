package com.wenky.example.base;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-14 10:12
 */
public class StringTest {
    public static void tt() {
        String aa = new String("pp") + new String("ll");
        System.out.println((new String("p") + new String("pll")).intern() == aa.intern());

        String ss = new String("stringzqsqxwdqwdxqd");
        // .intern 判断常量是否存在于常量池
        // 存在(引用：返回引用地址指向对空间对象 常量：返回常量池常量)
        // 不存在(当前对象引用复制到常量池，返回当前对象的引用)
        String s1 = ss.intern();
        String s = "stringzqsqxwdqwdxqd";
        System.out.println(s1 == s);
        System.out.println(s1 == new String(s).intern());
        System.out.println(s == ss);
        System.out.println(ss.intern() == s);
        System.out.println(s.length());

        String b = "AA" + "BB";
        String a = "AABB";
        System.out.println(a == b);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("bb");
        kk(sb);
        System.out.println(sb.toString());
        tt(sb);
        System.out.println(sb.toString());
    }

    public static void kk(StringBuilder sb) {
        sb = new StringBuilder("aa");
    }

    public static void tt(StringBuilder sb) {
        sb = sb.append("aa");
    }
}
