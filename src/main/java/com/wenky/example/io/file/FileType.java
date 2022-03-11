package com.wenky.example.io.file;

import java.io.File;
import java.util.stream.Stream;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-14 22:34
 */
public class FileType {

    public static void tt() {
        String fileName = "/Users/huwenqi";
        File file = new File(fileName);
        Stream.of(file.listFiles())
                .forEach(
                        f -> {
                            if (f.isDirectory()) {
                                System.out.println("我是个文件夹" + f.getName());
                            }
                            if (f.isFile()) {
                                System.out.println("我是个文件" + f.getName());
                            }
                        });
    }

    public static void main(String[] args) {
        tt();
    }
}
