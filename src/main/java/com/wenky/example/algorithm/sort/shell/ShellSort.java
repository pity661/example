package com.wenky.example.algorithm.sort.shell;

import com.wenky.example.algorithm.sort.SortUtils;

/**
 * @program: example
 * @description: 希尔排序(不是稳定排序算法)
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-26 17:48
 */
public class ShellSort {

  // 冒泡顺序交换
  public static void bubblingSort(int[] array) {
    // Hibbard增量,最坏时间复杂度 O(n^3/2)
    // 1，3，7，15 => 2^k - 1
    int gap = increase(array.length) - 1;
    while (gap > 0) {
      for (int i = gap; i < array.length; i++) {
        int j = i;
        // 交换法，像冒泡排序(按比例间隔进行比较)
        while (j - gap >= 0 && array[j] < array[j - gap]) {
          array[j] = array[j] + array[j - gap];
          array[j - gap] = array[j] - array[j - gap];
          array[j] = array[j] - array[j - gap];
          j -= gap;
        }
      }
      // (位移运算比加减优先级低)
      gap = (gap + 1 >> 1) - 1;
    }
  }

  // 插入整体后移
  public static void sort(int[] array) {
    // Hibbard增量,最坏时间复杂度 O(n^3/2)
    // 1，3，7，15 => 2^k - 1
    int gap = increase(array.length) - 1;
    while (gap > 0) {
      for (int i = gap; i < array.length; i++) {
        int j = i;
        // 当前待处理的数据
        int temp = array[i];
        // 插入移动法
        if (array[j] < array[j - gap]) {
          while (j - gap >= 0 && temp < array[j - gap]) {
            // 比temp大的都往后移动
            array[j] = array[j - gap];
            // temp跟前面的继续比较
            j -= gap;
          }
          // 插入值
          array[j] = temp;
        }
      }
      // (位移运算比加减优先级低)
      gap = (gap + 1 >> 1) - 1;
    }
  }

  public static void main(String[] args) {
    SortUtils.handleSort(ShellSort::sort);
  }

  private static int increase(int n) {
    n = n - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return n < 0 ? 1 : n + 1;
  }
}
