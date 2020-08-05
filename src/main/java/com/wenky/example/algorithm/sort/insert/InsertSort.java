package com.wenky.example.algorithm.sort.insert;

import com.wenky.example.algorithm.sort.SortUtils;

/**
 * @program: example
 * @description: 1插入排序：平均时间复杂度 O(n^2)
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-26 12:40
 */
public class InsertSort {

  /**
   * 前半段看成一个有序列表，遍历后段每个元素插入有序列表
   *
   * @param array
   */
  public static void sort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int temp = array[i], j = i;
      while (j - 1 >= 0 && temp < array[j - 1]) {
        // 往后移
        array[j] = array[j - 1];
        j--;
      }
      // 插入
      array[j] = temp;
    }
  }

  public static void main(String[] args) {
    SortUtils.handleSort(InsertSort::sort);
  }
}
