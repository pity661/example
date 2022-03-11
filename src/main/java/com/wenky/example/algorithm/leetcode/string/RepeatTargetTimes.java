package com.wenky.example.algorithm.leetcode.string;

import java.util.Stack;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-28 10:43
 */
public class RepeatTargetTimes {
    //
    public static void main(String[] args) {
        String s = "3[a]2[b4[F]c]";
        Stack<String> resStack = new Stack<>(); // 记录当前结果字符串
        Stack<Integer> repeatNum = new Stack<>();
        int rnum = 0;
        String res = ""; // 需要重复的字符串
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                resStack.push(res);
                repeatNum.push(rnum);
                res = "";
                rnum = 0;
            } else if (ch == ']') {
                // 开始循环,拼接字符串
                int n = repeatNum.pop();
                String tmp = resStack.pop();
                String str = "";
                for (; n > 0; n--) {
                    str += res;
                }
                res = tmp + str;
            } else if (ch >= '0' && ch <= '9') {
                // 循环次数
                // char 转成 int 是基础操作, 要牢记
                rnum = 10 * rnum + ch - '0';
            } else {
                // 字符串
                res = res + ch;
            }
        }
        System.out.println(res);
    }
}
