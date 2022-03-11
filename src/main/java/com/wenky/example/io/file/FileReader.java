package com.wenky.example.io.file;

import java.io.*;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-14 22:47
 */
public class FileReader {
    public static String fileName = "/Users/huwenqi/Desktop/file.txt";

    public static void readLine() throws IOException {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            System.out.println("start");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("end");
        }
    }

    public static void main(String[] args) throws IOException {
        readLine();
    }
}
