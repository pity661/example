package com.wenky.example.base.stream.supplier;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-25 10:07
 */
public class BaseTest {
    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> 1;
        List<Integer> result = Stream.generate(supplier).limit(10).collect(Collectors.toList());
        System.out.println(result);
    }
}
