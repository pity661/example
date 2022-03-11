package com.wenky.example.algorithm.leetcode.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-23 13:44
 */
public class FirstUniqChar {

    /**
     * find first uniq char from string return index
     *
     * @param s
     * @return
     */
    public static Integer firstUniqChar(String s) {
        Map<Character, Integer> cache = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character target = s.charAt(i);
            if (cache.keySet().contains(target)) {
                cache.put(target, -1);
            } else {
                cache.put(target, i);
            }
        }
        return cache.values().stream().filter(i -> i != -1).findFirst().orElse(-1);
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("aaedsxqdaawd"));
    }
}
