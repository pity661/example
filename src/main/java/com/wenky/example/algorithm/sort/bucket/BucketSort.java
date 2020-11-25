package com.wenky.example.algorithm.sort.bucket;

import com.wenky.example.algorithm.sort.SortUtils;
import com.wenky.example.algorithm.sort.insert.InsertSort;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program: example
 * @description: 1桶排序
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-26 18:44
 */
public class BucketSort {
  public static void sort(int[] array) {
    sort(array, 5);
  }

  /**
   * 将待排序原数组按间隔分散到桶中，对桶进行排序，合并所有桶中数据
   *
   * @param array
   * @param gap 指定数据间隔
   */
  public static void sort(int[] array, int gap) {
    // 找到极值，根据间隔确定桶的数量
    int min = getTheLimit(array, (a, b) -> a < b);
    int max = getTheLimit(array, (a, b) -> a > b);
    // 桶的个数
    int count = (max - min) / gap + 1;
    // 生成指定数量的桶
    List<List<Integer>> bucketList =
        Stream.generate(() -> new ArrayList<Integer>()).limit(count).collect(Collectors.toList());
    // 把所有待排序元素分散到桶中
    for (int i = 0; i < array.length; i++) {
      bucketList.get((array[i] - min) / gap).add(array[i]);
    }
    // 分别对各个桶中的元素进行插入排序
    List<int[]> sortedList =
        bucketList.stream()
            .map(noRuleList -> noRuleList.stream().mapToInt(Integer::intValue).toArray())
            .collect(Collectors.toList());
    sortedList.forEach(InsertSort::sort);
    List<Integer> result =
        sortedList.stream().flatMap(arr -> IntStream.of(arr).boxed()).collect(Collectors.toList());
    // return时使用
    // array = result.stream().mapToInt(Integer::intValue).toArray();
    for (int i = 0; i < array.length; i++) {
      array[i] = result.get(i);
    }
  }

  // 找到最大值和最小值
  private static int getTheLimit(int[] array, BiPredicate<Integer, Integer> predicate) {
    int limit = array[0];
    for (int i = 1; i < array.length; i++) {
      if (predicate.test(array[i], limit)) {
        limit = array[i];
      }
    }
    return limit;
  }

  public static void main(String[] args) {
    SortUtils.handleSort(BucketSort::sort);
  }
}
