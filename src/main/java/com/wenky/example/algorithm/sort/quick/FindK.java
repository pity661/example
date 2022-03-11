package com.wenky.example.algorithm.sort.quick;

import com.wenky.example.algorithm.sort.SortUtils;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description: 寻找第K大的数
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-07 14:12
 */
public class FindK {

    public static void main(String[] args) {
        FindK findK = new FindK();
        System.out.println(findK.findKth(new int[] {1, 3, 5, 2, 2}, 5, 3));
        // System.out.println(findK.findKth(new
        // int[]{1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663},49,24));
    }

    // 问题描述 https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf
    public int findKth(int[] a, int n, int K) {
        // write code here
        quickSort(0, n - 1, a, K);
        SortUtils.println(a);
        // 1.寻找第K大的数
        return a[n - K];
        // 2.寻找第K小的数
        // return a[K - 1];
    }

    private void quickSort(int begin, int end, int[] a, int k) {
        if (begin >= end) {
            return;
        }
        int i = begin, j = end;
        int tag = a[begin];
        while (i < j) {
            // 这里必须要先j--
            // 从右边开始找到比tag小的数
            while (tag <= a[j] && i < j) {
                j--;
            }
            // 从左边开始找到比tag大的数
            while (tag >= a[i] && i < j) {
                i++;
            }
            if (i < j) {
                a[j] = a[j] ^ a[i];
                a[i] = a[i] ^ a[j];
                a[j] = a[j] ^ a[i];
            }
        }
        a[begin] = a[i];
        a[i] = tag;
        System.out.printf("i:%d", i);
        System.out.println();
        SortUtils.println(a);
        // 1.寻找第K大的数
        if (i < a.length - k) {
            // 2.寻找第K小的数
            // if (i < k - 1) {
            // i + 1 ~ end
            quickSort(i + 1, end, a, k);
        } else {
            // begin ~ i - 1
            quickSort(begin, i - 1, a, k);
        }
    }
}
