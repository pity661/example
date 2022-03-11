package com.wenky.example.algorithm.sort.quick;

import java.util.LinkedList;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-23 21:19
 */
public class Stack {

    LinkedList list = new LinkedList();

    public synchronized void push(Object x) {
        synchronized (list) {
            list.addLast(x);
            notify();
        }
    }

    public synchronized Object pop() throws Exception {
        synchronized (list) {
            if (list.size() <= 0) {
                wait();
            }
            return list.removeLast();
        }
    }
}
