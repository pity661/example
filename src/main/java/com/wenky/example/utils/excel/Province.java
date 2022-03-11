package com.wenky.example.utils.excel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-24 14:57
 */
public class Province {
    private static void handle() {
        String formatP =
                "(now(),now(), 1, 0, '%s', '%s', 'CODE_CITY_MX_0', '[0-mexico] province', %s, true),";
        String formatC =
                "(now(),now(), 1, 0, '%s', '%s', 'CODE_CITY_MX_%s', '[%s-%s] city', %s, true),";
        Integer targetP = 9;
        Integer targetC = 1000;
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader reader =
                new BufferedReader(new FileReader("/Users/huwenqi/Desktop/墨西哥省市.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arg = line.split("\\|");
                if (Boolean.FALSE == map.containsKey(arg[0])) {
                    map.put(arg[0], ++targetP);
                    System.out.println(String.format(formatP, targetP, arg[0], targetP));
                }
                System.out.println(
                        String.format(
                                formatC, ++targetC, arg[1], targetP, targetP, arg[0], targetC));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        handle();
    }
}
