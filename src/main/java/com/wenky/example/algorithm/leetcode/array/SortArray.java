package com.wenky.example.algorithm.leetcode.array;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-12 09:43
 */
public class SortArray {
    // 题目：数组元素个数为偶数，排序后偶数在偶数位上奇数在奇数位上

    // 思路：只要遍历偶数位上的数，用数组后的奇数位上的偶数进行替换
    public int[] sortArray(int[] A) {
        // 第一个奇数位
        int j = 1;
        // 遍历所有偶数位
        for (int i = 0; i < A.length - 1; i += 2) {
            // 位运算进行偶数判断
            if ((A[i] & 1) != 0) {
                // 遍历所有奇数位进行替换
                while ((A[j] & 1) != 0) {
                    j += 2;
                }
                A[i] = A[i] + A[j];
                A[j] = A[i] - A[j];
                A[i] = A[i] - A[j];
            }
        }
        return A;
    }
}
