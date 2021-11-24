package com.wenky.example.base.stream.supplier;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-25 14:52
 */
public class Randoms {

  public static List<Integer> getRandom() {
    return new Random()
        .ints(5, 20)
        .distinct()
        .limit(10)
        .sorted()
        //        .mapToObj(Integer::valueOf)
        .boxed()
        .collect(Collectors.toList());
  }

  public static void intStreamRange() {
    Integer sum = IntStream.range(1, 10).limit(3).sum();
    System.out.println(sum);
  }

  public static void main(String[] args) {
    System.out.println(getRandom());
    intStreamRange();
  }
}
