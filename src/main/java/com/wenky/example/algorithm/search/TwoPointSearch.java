package com.wenky.example.algorithm.search;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description: 二分查找
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-07 15:48
 */
public class TwoPointSearch {
    public int result = -1;

    // 问题描述 https://www.nowcoder.com/practice/4f470d1d3b734f8aaf2afb014185b395
    public void search(int start, int end, int[] array, int target) {
        if (start == end) {
            return;
        }
        if (end == 0) {
            return;
        }
        //        System.out.println(String.format("start: %d, end: %d", start, end));
        int mid = start + end >> 1;
        //        System.out.println(mid);
        if (array[mid] == target) {
            result = result == -1 ? mid : Math.min(result, mid);
        }
        if (array[mid] < target) {
            search(mid + 1, end, array, target);
        } else {
            search(start, mid, array, target);
        }
    }

    public static void main(String[] args) {
        TwoPointSearch twoPointSearch = new TwoPointSearch();
        int[] array = {1, 2, 2, 4};
        int target = 2;
        twoPointSearch.search(0, array.length, array, target);
        System.out.println(twoPointSearch.result);
    }
}
