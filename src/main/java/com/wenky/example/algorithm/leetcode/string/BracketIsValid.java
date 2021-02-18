package com.wenky.example.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-23 14:01
 */
public class BracketIsValid {

  public static boolean isValid(String s) {
    if (s.length() == 0) {
      return true;
    }
    List<Character> start = Arrays.asList('(', '{', '[');
    List<Character> end = Arrays.asList(')', '}', ']');
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      if (start.contains(c)) {
        stack.push(c);
      }
      if (end.contains(c)) {
        if (stack.empty()) {
          return false;
        }
        Character pre = stack.pop();
        if (c == ')' && pre == '(') {
          continue;
        }
        if (c == '}' && pre == '{') {
          continue;
        }
        if (c == ']' && pre == '[') {
          continue;
        }
        return false;
      }
    }
    return stack.empty();
  }

  public static void main(String[] args) {
    System.out.println(isValid("([)]"));
    System.out.println(isValid("()"));
    System.out.println(isValid("()[]{}"));
    System.out.println(isValid("(]"));
    System.out.println(isValid("([)]"));
  }
}
