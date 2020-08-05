package com.wenky.example.base.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.util.StopWatch;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-06-08 09:43
 */
public class ParallelTest {

  public static int parallelSum(List<Integer> integerList) {
    int sum =
        integerList
            .parallelStream()
            .mapToInt(Integer::intValue)
            //                .sum();
            .reduce(0, (a, b) -> a + b);
    return sum;
  }

  public static int parallelSum1(List<Integer> integerList) {
    int sum =
        integerList.stream()
            //            .parallel()
            .sequential() // 顺序执行
            .mapToInt(Integer::intValue)
            .reduce(0, (a, b) -> a + b);
    return sum;
  }

  public static void sumTest() {
    List<Integer> list = Stream.of(12, 1).collect(Collectors.toList());
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(() -> handle((l) -> parallelSum1(l), list));
    executor.submit(() -> handle((l) -> parallelSum(l), list));
    executor.shutdown();
  }

  public static void taskTest() {
    List<Consumer<Integer>> list = new ArrayList<>();
    list.add(sleep());
    list.add(sleep());
    list.add(sleep());
    list.add(sleep());
    list.add(sleep());
    list.add(sleep());
    list.add(sleep());

    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.submit(
        () ->
            handle(
                (l) -> {
                  l.stream().parallel().forEach(s -> s.accept(1));
                  return "完成";
                },
                list));

    executor.submit(
        () ->
            handle(
                (l) -> {
                  l.stream().forEach(s -> s.accept(1));
                  return "完成";
                },
                list));
    executor.shutdown();
  }

  public static Consumer<Integer> sleep() {
    return (num) -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(num);
    };
  }

  public static void main(String[] args) {
    taskTest();
  }

  private static <T, R> void handle(Function<List<T>, R> function, List<T> list) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    System.out.println("result:" + function.apply(list));
    stopWatch.stop();
    System.out.println(stopWatch.getTotalTimeMillis());
  }
}
