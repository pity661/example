package com.wenky.example.algorithm.leetcode.dp;

import java.util.*;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-11 16:53
 */
public class FindShortRoute {
    String ring;
    String key;
    Map<Character, List<Integer>> ringMap = new HashMap<>();

    // 问题描述 https://leetcode-cn.com/problems/freedom-trail/
    public int find(String ring, String key) {
        this.ring = ring;
        this.key = key;
        // 把 ring存入map
        for (int i = 0; i < ring.length(); i++) {
            char single = ring.charAt(i);
            if (ringMap.containsKey(single)) {
                ringMap.get(single).add(i);
            } else {
                ringMap.put(single, new ArrayList<>(Arrays.asList(i)));
            }
        }
        // 开始执行的初始位置值
        return key.length() + dps(0, 0);
    }

    Map<String, Integer> cacheMap = new HashMap<>();

    public int dps(int ringI, int keyI) {
        System.out.println(String.format("ringI:[%d], keyI:[%d]", ringI, keyI));
        // 动态规划结束条件
        if (keyI == key.length()) {
            return 0;
        }
        Integer result;
        // 优化点 根据传入的参数缓存返回结果
        if ((result = cacheMap.get(ringI + " " + keyI)) != null) {
            return result;
        }
        char target = key.charAt(keyI);
        Integer max = Integer.MAX_VALUE;
        for (Integer targetI : ringMap.get(target)) {
            // 正着
            int d1 = Math.abs(ringI - targetI);
            // 反着
            int d2 = ring.length() - d1;
            int curMin = Math.min(d1, d2);
            max = Math.min(max, curMin + dps(targetI, keyI + 1));
        }
        cacheMap.put(ringI + " " + keyI, max);
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new FindShortRoute().find("abc", "ab"));
    }
}
