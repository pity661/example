package com.wenky.example.base.inner;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-10-21 10:14
 */
public class InnerClass {
    private int count = 0;

    Counter getCounter1(final String name) {
        class LocalCounter implements Counter {
            LocalCounter() {
                System.out.println("LocalCounter()");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        }
        return new LocalCounter();
    }

    Counter getCounter2(String name) {
        return new Counter() {
            {
                System.out.println("Counter()");
            }

            @Override
            public int next() {
                System.out.print(name);
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        Counter counter1 = innerClass.getCounter1("counter1 "),
                counter2 = innerClass.getCounter2("counter2 ");
        for (int i = 0; i < 5; i++) {
            System.out.println(counter1.next());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(counter2.next());
        }
    }
}
