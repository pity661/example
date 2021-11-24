package com.wenky.example.algorithm.leetcode.string;

import java.util.HashMap;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description: 滑动窗口解决字符串最小包含子串问题
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-25 14:58
 */
public class MinMatchInString {

  public static String handle(String s, String t) {
    HashMap<Character, Integer> need = new HashMap<>();
    HashMap<Character, Integer> window = new HashMap<>();
    // t可能包含多个重复的字符，所以要记录字符数量
    for (char c : t.toCharArray()) {
      need.put(c, need.getOrDefault(c, 0) + 1);
    }
    int left = 0, right = 0;
    int valid = 0;
    // 记录最小覆盖字串的起始索引及长度
    int start = 0, len = Integer.MAX_VALUE;
    while (right < s.length()) {
      char c = s.charAt(right);
      right++;
      // 判断取出的字符是否在字串中
      if (need.containsKey(c)) {
        window.put(c, window.getOrDefault(c, 0) + 1);
        // 只有字符数量一致的时候 valid才会记录
        if (window.get(c).equals(need.get(c))) {
          valid++;
        }
      }
      // 判断字符种类是否需要收缩（已经找到合适的覆盖串）
      while (valid == need.size()) {
        if (right - left < len) {
          start = left;
          len = right - left;
        }
        char c1 = s.charAt(left);
        left++;
        if (need.containsKey(c1)) {
          // 如果字符数量一致进行处理
          if (window.get(c1).equals(need.get(c1))) {
            valid--;
          }
          // 删除后要把对应字符数量 - 1
          window.put(c1, window.getOrDefault(c1, 0) - 1);
        }
      }
    }
    return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
  }

  public static void main(String[] args) {
    System.out.println(handle("ADOBECODEBANC", "ABC"));

    System.out.println(handle("a", "a"));

    System.out.println(handle("aa", "aa"));
  }
}
