package com.wenky.example.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-11 18:28
 */
public class FindArraySum {
  // 问题描述 提供一个数组，找出数组中相加值为指定结果的地址
  public int[] findAddIndex(int[] array, int target) {
    // target - value index
    Map<Integer, Integer> cache = new HashMap<>();
    for (int i = 0; i < array.length; i++) {
      Integer complement = target - array[i];
      if (cache.containsKey(complement)) {
        return swap(i, cache.get(complement));
      }
      cache.put(complement, i);
    }
    return null;
  }

  private int[] swap(Integer a, Integer b) {
    return a < b ? new int[] {a, b} : new int[] {b, a};
  }
}
