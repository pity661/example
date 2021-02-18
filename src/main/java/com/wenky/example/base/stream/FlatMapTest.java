package com.wenky.example.base.stream;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-25 15:52
 */
public class FlatMapTest {
  public static void flatMap() {
    Supplier<List<Integer>> supplier =
        () -> IntStream.range(1, 3).limit(5).boxed().collect(Collectors.toList());
    Supplier<List<Integer>> supplier1 =
        () -> new Random().ints(1, 6).limit(2).boxed().collect(Collectors.toList());
    List<List<Integer>> lists = Stream.generate(supplier1).limit(5).collect(Collectors.toList());
    List<Integer> list =
        lists.stream().flatMap(single -> single.stream()).collect(Collectors.toList());
    List<Integer> list1 =
        lists.stream()
            .flatMap(
                single ->
                    IntStream.concat(single.stream().mapToInt(Integer::intValue), IntStream.of(-1))
                        .boxed())
            .collect(Collectors.toList());
    System.out.println(list);
    System.out.println(list1);
  }

  public static void main(String[] args) {
    flatMap();
  }
}
