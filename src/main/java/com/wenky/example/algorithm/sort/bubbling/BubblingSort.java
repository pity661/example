package com.wenky.example.algorithm.sort.bubbling;

/**
 * @program: example
 * @description: 1冒泡排序
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-26 11:23
 */
public class BubblingSort {

  /**
   * 冒泡排序，遍历数组，当前元素与剩余元素比较，如果遇到极值则替换
   *
   * @param array
   */
  public static void sort(int[] array) {
    for (int i = 0; i < array.length; ++i) {
      for (int j = i; j < array.length; j++) {
        if (array[i] > array[j]) {
          array[i] = array[i] ^ array[j];
          array[j] = array[j] ^ array[i];
          array[i] = array[i] ^ array[j];
        }
      }
    }
  }
}
