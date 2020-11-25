package com.wenky.example.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-16 09:44
 */
public class SortPeople {
  // 题目：前面表示身高，后面表示身高比当前高的个数，需要按照顺序排列
  // 思路：1.先按照从高到低的顺序排列
  // 2.然后按照后面的值插入到数组指定位置
  // [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, ((o1, o2) -> (o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0])));
    System.out.println("first sort");
    Arrays.deepToString(people);
    List<int[]> res = new ArrayList<>();
    for (int[] arr : people) {
      res.add(arr[1], arr);
    }
    return res.toArray(new int[people.length][2]);
  }

  public static void main(String[] args) {
    int[][] people =
        new int[][] {
          new int[] {7, 0},
          new int[] {4, 4},
          new int[] {7, 1},
          new int[] {5, 0},
          new int[] {6, 1},
          new int[] {5, 2}
        };
    people = new SortPeople().reconstructQueue(people);
    System.out.println("sort result");
    System.out.println(Arrays.deepToString(people));
  }
}
