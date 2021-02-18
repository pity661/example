package com.wenky.example.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Random;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-11-25 17:58
 */
public class FileUtil {
  public static void simple() {
    System.out.println(System.getProperty("os.name"));
    System.out.println(Paths.get("FileUtil.java").getFileSystem().isReadOnly());
    System.out.println(Paths.get("FileUtil.java").toAbsolutePath());
    System.out.println(Files.isReadable(Paths.get("FileUtil.java")));
    System.out.println(Files.isDirectory(Paths.get("/Users/huwenqi")));
    System.out.println(Paths.get("../huwenqi").isAbsolute());
  }

  // 文件查找
  public static void globPathMatcher() throws IOException {
    Path test = Paths.get("src");
    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.{java,tmp,txt}");
    Files.walk(test).filter(matcher::matches).forEach(System.out::println);
    System.out.println(" ******************************** ");
    PathMatcher matcher1 = FileSystems.getDefault().getPathMatcher("glob:*.{tmp,txt}");
    Files.walk(test)
        .filter(Files::isRegularFile)
        .map(Path::getFileName)
        .filter(matcher1::matches)
        .forEach(System.out::println);
  }

  // 文件读取
  public static void readAllLine() throws IOException {
    Path test = Paths.get("src/main/resources/static/read-line.txt");
    Files.readAllLines(test).stream().forEach(System.out::println);
  }

  public static void lines() throws IOException {
    Path test = Paths.get("src/main/resources/static/read-line.txt");
    Files.lines(test).forEach(System.out::println);
  }

  // 文件写入
  public static void fileWrite() throws IOException {
    byte[] bytes = new byte[1000];
    new Random().nextBytes(bytes);
    Files.write(new File("src/main/resources/static/bytes.dat").toPath(), bytes);
    System.out.println(
        "bytes.dat: " + Files.size(Paths.get("src/main/resources/static/bytes.dat")));

    List<String> lines = Files.readAllLines(Paths.get("src/main/resources/static/read-line.txt"));
    Files.write(new File("src/main/resources/static/Cheese.txt").toPath(), lines);
    System.out.println(
        "Cheese.txt: " + Files.size(Paths.get("src/main/resources/static/Cheese.txt")));
  }

  public static void main(String[] args) throws IOException {
    //    globPathMatcher();
    //    readAllLine();
    lines();
    //    fileWrite();
  }
}
