package com.wenky.example.utils.file;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-08 10:34
 */
public class SqlLineHandle {

  public static void main(String[] args) throws IOException {
    readAllLine2();
  }

  // 文件读取
  public static void readAllLine() throws IOException {
    Path test = Paths.get("/Users/huwenqi/Desktop/111.txt");
    Files.readAllLines(test).stream()
        .forEach(
            s ->
                System.out.println(
                    String.format(
                        "update app_config.base_bundle set fb_id='%s' where bundle_id='%s';",
                        s.split(",")[1], s.split(",")[0])));
  }

  // 文件读取
  public static void readAllLine1() throws IOException {
    Path test = Paths.get("/Users/huwenqi/Desktop/a.txt");
    Files.readAllLines(test).stream()
        .filter(s -> !s.split(" ")[0].equals("0"))
        .forEach(
            s ->
                System.out.println(
                    new BigDecimal(s.split(" ")[0])
                        .divide(new BigDecimal(s.split(" ")[1]), 4, BigDecimal.ROUND_HALF_UP)
                        .toString()));
  }

  // 文件读取
  public static void readAllLine2() throws IOException {
    Path test = Paths.get("/Users/huwenqi/Desktop/aaa.txt");
    Files.readAllLines(test).stream()
        .forEach(
            s ->
                System.out.println(
                    String.format(
                        "update app_config.base_bundle set appsflyer_key = '%s' where bundle_id = '%s';",
                        s.split(" , ")[1], s.split(" , ")[0])));
  }
}
