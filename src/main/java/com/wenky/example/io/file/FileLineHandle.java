package com.wenky.example.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-02-18 13:01
 */
public class FileLineHandle {
    public static String readFileName = "/Users/huwenqi/Desktop/mysql-slowquery-2.log";

    public static String writeFileName = "/Users/huwenqi/Desktop/mysql-slowquery-handle.log";

    public static void handleLine() throws IOException {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(readFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(writeFileName, true)); ) {
            String line;
            System.out.println("start");
            while ((line = reader.readLine()) != null) {
                // 转化时间格式
                try {
                    if (StringUtils.isNotBlank(line) && line.startsWith("# Time: ")) {
                        line = "# Time: " + formatTime(line.split("# Time: ")[1]);
                    }
                } catch (Exception e) {
                    System.out.format("转化时间格式发生异常：[%s]", line);
                }
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        }
        System.out.println("end");
    }

    private static String formatTime(String s) {
        return s.substring(2, 4)
                + s.substring(5, 7)
                + s.substring(8, 10)
                + " "
                + s.substring(11, 19);
    }

    public static void main(String[] args) throws IOException {
        handleLine();
        //        System.out.println(formatTime("2021-02-02T04:00:04.080372Z"));
    }
}
