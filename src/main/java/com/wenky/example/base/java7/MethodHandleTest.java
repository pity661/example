package com.wenky.example.base.java7;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-06-09 15:17
 */
public class MethodHandleTest {
    public static void test() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle =
                lookup.findVirtual(String.class, "length", MethodType.methodType(int.class));
        int result = (int) methodHandle.invoke(new String("1"));
        System.out.println(result);
        //      (int) methodHandle.invoke(1);

        // 返回类型 + 参数类型[]
        MethodType methodType = MethodType.methodType(boolean.class, String.class);
        MethodHandle methodHandle1 = lookup.findVirtual(String.class, "startsWith", methodType);
        boolean result1 = (boolean) methodHandle1.invoke("1", "2");
        System.out.println(result1);
    }

    public static void main(String[] args) throws Throwable {
        Collections.synchronizedList(new ArrayList());

        // 加锁机制
        List list = new CopyOnWriteArrayList();
        test();
    }
}
