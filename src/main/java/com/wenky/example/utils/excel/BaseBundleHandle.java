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
 * @create: 2021-03-19 16:18
 */
public class BaseBundleHandle {
  public static void main(String[] args) {
    baseBundleHandle();
  }

  private static void baseBundleHandle() {
    try {
      FileInputStream inputStream = new FileInputStream("/Users/huwenqi/Desktop/在线app统计.xlsx");
      // HSSFWorkbook 一次性将整个excel读入内存
      Workbook workbook = WorkbookFactory.create(inputStream);
      Sheet sheet = workbook.getSheetAt(0);
      List<List<String>> list = FileRead.readSheet(sheet);
      System.out.println(list.size());
      //            list = list.subList(0, list.size());
      list.stream()
          .forEach(
              single -> {
                String s =
                    "update base_bundle set fb_id='"
                        + single.get(2).trim()
                        + "' where bundle_id='"
                        + single.get(1).trim()
                        + "' and app_type='android';";
                System.out.println(s);
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
