package com.wenky.example.utils.excel;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-28 11:13
 */
public class Paychannel {

  public static void main(String[] args) {
    read();
  }

  public static void read() {
    try {
      FileInputStream inputStream =
          new FileInputStream("/Users/huwenqi/Desktop/越南支付通道银行信息-0106.xlsx");
      // HSSFWorkbook 一次性将整个excel读入内存
      Workbook workbook = WorkbookFactory.create(inputStream);
      Sheet sheet = workbook.getSheetAt(0);
      List<List<String>> list = FileRead.readSheet(sheet);
      System.out.println(list.size());
      list.stream()
          .forEach(
              single -> {
                String s =
                    "('yoopay','lending','bank','"
                        + single.get(0)
                        + "','"
                        + new BigDecimal(single.get(11)).intValue()
                        + "','"
                        + single.get(12)
                        + "'),";
                System.out.println(s);
              });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
