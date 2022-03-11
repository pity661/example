package com.wenky.example.io.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-14 22:06
 */
public class FileWrite {
    public static String fileName = "/Users/huwenqi/Desktop/file.txt";

    public static void appendTest() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("aaa");
            writer.newLine();
            writer.write("bbb");
            writer.flush();
        }
    }

    public static void outPutStream() throws IOException {
        String fileName = "/Users/huwenqi/Desktop/file1.txt";
        try (OutputStreamWriter writer =
                new OutputStreamWriter(new FileOutputStream(fileName, true))) {
            writer.write("aaa");
            writer.flush();
        }
    }

    public static void write() throws IOException {
        String fileName = "/Users/huwenqi/Desktop/file2.txt";
        Files.write(
                new File(fileName).toPath(),
                Arrays.asList("aaa", "bbb"),
                StandardOpenOption.APPEND);
    }

    public static void delete() throws IOException {
        String fileName = "/123";
        // java.nio.file.NoSuchFileException
        //    Files.delete(new File(fileName).toPath());

        // false
        System.out.println(new File(fileName).delete());
    }

    public static void main(String[] args) throws IOException {
        //    appendTest();
        //    outPutStream();
        //    write();
        //    mkdirs();
        delete();
    }

    private static void mkdirs() throws IOException {
        String fileName = "/Users/huwenqi/Desktop/1/2/file2.txt";
        File file = new File(fileName);
        if (Boolean.FALSE == file.exists()) {
            // 执行生成 /Users/huwenqi/Desktop/1/2
            file.getParentFile().mkdirs();
            // 直接执行，当 1、2文件夹不存在时抛异常
            // file.createNewFile();
        }
    }
}
