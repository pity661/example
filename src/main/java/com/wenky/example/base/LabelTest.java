package com.wenky.example.base;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-14 09:34
 */
public class LabelTest {

    public static void forLabel() {
        retry:
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (i == 5) {
                break retry; // continue 同样适用
            }
        }
    }

    public static void whileLabel() {
        int i = 0;
        retry:
        while (i++ < 10) {
            System.out.println(i);
            if (i == 5) {
                break retry; // continue 同样适用
            }
        }
    }

    public static void doWhileLabel() {
        int i = 0;
        retry:
        do {
            i++;
            System.out.println(i);
            if (i == 5) {
                break retry; // continue 同样适用
            }
        } while (i < 10);
    }

    public static void main(String[] args) {
        //       forLabel();
        //       whileLabel();
        doWhileLabel();
    }
}
