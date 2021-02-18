package com.wenky.example.utils.excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-06-03 14:12
 */
public class FileRead {
  public static void main(String[] args) {
    try {
      FileInputStream inputStream = new FileInputStream("/Users/huwenqi/Desktop/test.xlsx");
      // HSSFWorkbook 一次性将整个excel读入内存
      Workbook workbook = WorkbookFactory.create(inputStream);
      for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
        String sheetName = workbook.getSheetName(i);
        Sheet sheet = workbook.getSheetAt(i);
        List<List<String>> dataList = readSheet(sheet);
        ExcelAnalysisResult excelAnalysisResult =
            new ExcelAnalysisResult().initData(sheetName, dataList);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static List<List<String>> readSheet(Sheet sheet) {
    List<List<String>> result = new ArrayList<>();
    List<String> rowList;
    for (int i = 2; i <= sheet.getLastRowNum(); i++) {
      rowList = new ArrayList<>();
      Row row = sheet.getRow(i);
      Iterator iterator = row.cellIterator();
      while (iterator.hasNext()) {
        rowList.add(iterator.next().toString());
      }
      result.add(rowList);
    }
    return result;
  }
}
