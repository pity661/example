package com.wenky.example.algorithm.sort.merge;

/**
 * @program: example
 * @description: 1归并排序算法，O(nlogn)
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-25 14:27
 */
public class MergeSort {

  /**
   * 1.递归分到很小的数组(2个)2.主要是合并算法：左边从left开始，右边从mid+1开始，比较放在左边。然后把剩余没比较的加在后面
   *
   * @param array
   */
  public static void sort(int[] array) {
    mergeSort(0, array.length - 1, array);
  }

  private static void mergeSort(int start, int end, int[] array) {
    if (start < end) {
      int mid = (start + end) >> 1;
      mergeSort(start, mid, array);
      mergeSort(mid + 1, end, array);
      merge(start, mid, end, array);
    }
  }

  // 归并的左右两边都是已经排好序的
  private static void merge(int left, int mid, int right, int[] array) {
    int[] temp = new int[right + 1];
    int k = left, p1 = left, p2 = mid + 1;
    // 从mid开始，一左一右对比
    while (p1 <= mid && p2 <= right) {
      if (array[p1] <= array[p2]) {
        temp[k++] = array[p1++];
      } else {
        temp[k++] = array[p2++];
      }
    }
    while (p1 <= mid) {
      temp[k++] = array[p1++];
    }
    while (p2 <= right) {
      temp[k++] = array[p2++];
    }
    for (int i = left; i <= right; i++) {
      array[i] = temp[i];
    }
  }
}
