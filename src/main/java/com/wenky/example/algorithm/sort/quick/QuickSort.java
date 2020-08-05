package com.wenky.example.algorithm.sort.quick;

import com.wenky.example.algorithm.sort.SortUtils;

/**
 * @program: example
 * @description: 1快速排序算法
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-25 15:17
 */
public class QuickSort {

  /**
   * 1.先找到一个固定的点(如最左边)。并记录begin标点值 2.j从右边开始倒回来找比标点小的(找到停止)，i从左边顺着找比标点大的，找到交换i，j。(这个过程中i一定不能超过j) 3.当i
   * == j时结束 4.交换i坐标和begin的位置(交换后i坐标也就是标示点一定是中间点，不用再参与排序了)
   *
   * @param array
   */
  public static void sort(int[] array) {
    sort(0, array.length - 1, array);
  }

  private static void sort(int begin, int end, int[] array) {
    if (begin >= end) {
      return;
    }
    int i = begin, j = end;
    int tag = array[begin];
    while (i < j) {
      while (tag <= array[j] && i < j) {
        j--;
      }
      while (tag >= array[i] && i < j) {
        i++;
      }
      if (i < j) {
        array[j] = array[j] ^ array[i];
        array[i] = array[i] ^ array[j];
        array[j] = array[j] ^ array[i];
      }
    }
    // 这里不能用swap方式，因为array[begin]和array[i]可能是同一个对象
    array[begin] = array[i];
    array[i] = tag;

    SortUtils.println(array);

    sort(begin, j - 1, array);
    sort(j + 1, end, array);
  }

  public static void main(String[] args) {
    int[] a = {6, 1, 5, 4, 3, 7};
    sort(a);
    SortUtils.println(a);
  }
}
