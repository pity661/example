package com.wenky.example.algorithm.sort.quick;

import com.wenky.example.algorithm.sort.SortUtils;

/**
 * @program: example
 * @description: 1快速排序算法
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-05-25 15:17
 */
public class QuickSort {

    /**
     * 1.先找到一个固定的点(如最左边)。并记录begin标点值
     *
     * <p>2.j从右边开始倒回来找比标点小的(找到停止,必须从右边开始)，i从左边顺着找比标点大的，找到交换i，j。(这个过程中i一定不能超过j)
     *
     * <p>3.当i == j时结束
     *
     * <p>4.交换i坐标和begin的位置(交换后i坐标也就是标示点一定是该元素排序后应在点，不用再参与排序了)
     *
     * @param array
     */
    public static void sort(int[] array) {
        sort(0, array.length - 1, array);
    }

    private static void sort(int begin, int end, int[] array) {
        if (begin >= end) {
            return;
        }
        int i = begin, j = end;
        int tag = array[begin];
        // 将比标点小的数移到标点左边，比标点大的数移到标点右边 开始
        while (i < j) {
            while (tag <= array[j] && i < j) {
                j--;
            }
            while (tag >= array[i] && i < j) {
                i++;
            }
            if (i < j) {
                array[j] = array[j] ^ array[i];
                array[i] = array[i] ^ array[j];
                array[j] = array[j] ^ array[i];
            }
            SortUtils.println(array);
        }
        // 此时 i跟j一定相等 i === j

        // 这里不能用swap方式，因为array[begin]和array[i]可能是同一个对象
        // 将标点移动到他应该在的位置
        array[begin] = array[i];
        array[i] = tag;
        // 将比标点小的数移到标点左边，比标点大的数移到标点右边 结束

        System.out.println("w");
        System.out.println(String.format("i:%s, j:%d", i, j));
        SortUtils.println(array);

        // 标点左边的快排处理
        sort(begin, j - 1, array);
        // 标点右边的快排处理
        sort(j + 1, end, array);
    }

    public static void main(String[] args) {
        //    int[] a = {6, 1, 5, 4, 3, 7};
        //    sort(a);
        //    SortUtils.println(a);

        //    SortUtils.handleSort(QuickSort::sort);

        //    int[] a =
        //        new int[] {
        //          1332802, 1177178, 1514891, 871248, 753214, 123866, 1615405, 328656, 1540395,
        // 968891,
        //          1884022, 252932, 1034406, 1455178, 821713, 486232, 860175, 1896237, 852300,
        // 566715,
        //          1285209, 1845742, 883142, 259266, 520911, 1844960, 218188, 1528217, 332380,
        // 261485,
        //          1111670, 16920, 1249664, 1199799, 1959818, 1546744, 1904944, 51047, 1176397,
        // 190970,
        //          48715, 349690, 673887, 1648782, 1010556, 1165786, 937247, 986578, 798663
        //        };
        //    sort(a);
        //    SortUtils.println(a);
        System.out.println(transform(15));
    }

    // 10进制转成2进制里面有几个1
    public static int transform(int number) {
        int count = 0;
        do {
            if (number % 2 == 1) {
                count++;
            }
        } while ((number = number >> 1) != 0);
        return count;
    }
}
