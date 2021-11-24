package com.wenky.example.utils.excel;

import java.io.FileInputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-04-08 14:35
 */
public class City2Sql {

  public static void main(String[] args) {
    city2Sql();
  }

  public static void city2Sql() {
    try (FileInputStream inputStream =
        new FileInputStream("/Users/huwenqi/Desktop/副本泰国增加的省市0407.xlsx")) {
      // HSSFWorkbook 一次性将整个excel读入内存
      Workbook workbook = WorkbookFactory.create(inputStream);
      Sheet sheet = workbook.getSheetAt(0);
      List<List<String>> list = FileRead.readSheet(sheet);
      System.out.println(list.size());
      list.stream()
          .forEach(
              single -> {
                String s =
                    "(0, 0, '"
                        + single.get(1)
                        + "', '"
                        + single.get(0)
                        + "','"
                        + single.get(2)
                        + "','"
                        + single.get(3)
                        + "',"
                        + "1, true),";
                System.out.println(s);
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
