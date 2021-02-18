package com.wenky.example.io.file;

import java.io.*;

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
    }
  }

  public static void outPutStream() throws IOException {
    String fileName = "/Users/huwenqi/Desktop/file1.txt";
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName, true))) {
      writer.write("aaa");
    }
  }

  public static void main(String[] args) throws IOException {
    appendTest();
    //    outPutStream();
  }
}
