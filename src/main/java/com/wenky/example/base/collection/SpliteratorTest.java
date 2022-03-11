package com.wenky.example.base.collection;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-20 10:13
 */
public class SpliteratorTest {
    AtomicInteger atomicInteger = new AtomicInteger(0);
    List<String> list = createList();
    Spliterator spliterator = list.spliterator();

    private List<String> createList() {
        List list = new ArrayList();
        for (int i = 0; i < 150; i++) {
            list.add(i % 10 == 0 ? String.valueOf(i) : "aa");
        }
        return list;
    }

    public void test() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            executor.execute(
                    () -> {
                        String name = Thread.currentThread().getName();
                        System.out.println("线程：" + name + " 开始启动运行");
                        spliterator
                                .trySplit()
                                .forEachRemaining(
                                        l -> {
                                            if (StringUtils.isNumeric((CharSequence) l)) {
                                                System.out.println("我是数字：" + l + "线程：" + name);
                                                try {
                                                    Thread.sleep(2000);
                                                    atomicInteger.addAndGet(1);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                        System.out.println("线程：" + name + " 运行结束");
                        countDownLatch.countDown();
                    });
        }
        countDownLatch.await();
        executor.shutdown();
        System.out.println("主线程退出了");
        System.out.println(atomicInteger.intValue());
    }

    public static void main(String[] args) throws InterruptedException {
        //    new SpliteratorTest().test();
        //      System.out.println(1 << 30);
        //      System.out.println(Integer.MAX_VALUE);
        System.out.println(4 >>> 3);
    }

    public void bitOperateTest() {
        //      ConcurrentHashMap
        //      Collections.synchronizedMap();
        //      LinkedHashSet
        //      TreeSet
        //      HashSet
        int a = 0x010;
        System.out.println(a);
        int b = 0x011;
        System.out.println(a & b);
        int c = 0x040;
        // 先都转化为2进制再进行或运算
        System.out.println(a | c);
    }
}
