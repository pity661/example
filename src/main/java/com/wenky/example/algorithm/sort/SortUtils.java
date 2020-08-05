package com.wenky.example.algorithm.sort;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-26 12:10
 */
public class SortUtils {

  public static final int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

  public static void main(String[] args) {
    shuffle(a);
  }

  public static int[] shuffle(int[] a) {
    List<Integer> list = IntStream.of(a).mapToObj(Integer::new).collect(Collectors.toList());
    Collections.shuffle(list);
    a = list.stream().mapToInt(Integer::intValue).toArray();
    return a;
  }

  public static void println(int[] a) {
    System.out.println(IntStream.of(a).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
  }

  public static void handleSort(Consumer<int[]> consumer) {
    int[] a = SortUtils.shuffle(SortUtils.a);
    SortUtils.println(a);
    consumer.accept(a);
    SortUtils.println(a);
  }
}
