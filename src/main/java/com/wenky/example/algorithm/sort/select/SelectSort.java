package com.wenky.example.algorithm.sort.select;

import com.wenky.example.algorithm.sort.SortUtils;

/**
 * @program: example
 * @description: 1选择排序
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-26 12:57
 */
public class SelectSort {
  /**
   * 1.固定从第一个元素开始，往后遍历2.每次遍历，后续元素进行比较3.选取最小的排在前面
   *
   * @param array
   */
  public static void sort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int flag = i;
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < array[flag]) {
          // 比较记录
          flag = j;
        }
      }
      if (flag != i) {
        // 最后交换一次
        array[i] = array[i] ^ array[flag];
        array[flag] = array[flag] ^ array[i];
        array[i] = array[i] ^ array[flag];
      }
    }
  }

  public static void main(String[] args) {
    SortUtils.handleSort(SelectSort::sort);
  }
}
