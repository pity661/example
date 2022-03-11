package com.wenky.example.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-16 10:24
 */
public class LengthOfLongest {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> cache = new HashMap<>();
        int i = -1, ans = 0;
        for (int j = 0; j < s.length(); j++) {
            if (cache.containsKey(s.charAt(j))) {
                // i指针可能往后走了，get的值会回去。所以要取较大的值
                i = Math.max(cache.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i);
            cache.put(s.charAt(j), j);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongest().lengthOfLongestSubstring("abbac"));
    }
}
