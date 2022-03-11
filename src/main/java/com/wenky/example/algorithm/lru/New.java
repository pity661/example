package com.wenky.example.algorithm.lru;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description: 阿里一面台阶算法
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-23 21:28
 */
public class New {
    public static int jump(int target) {
        if (target == 1 || target == 0) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return jump(target - 1) + jump(target - 2) + jump(target - 3);
    }

    public static void main(String[] args) {
        // 12 21 3 111  4
        // 1111 13 31 121 211 112 22 7
        System.out.println(jump(4));
    }
}
