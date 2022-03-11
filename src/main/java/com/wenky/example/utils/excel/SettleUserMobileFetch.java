package com.wenky.example.utils.excel;

import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 10:26
 */
public class SettleUserMobileFetch {
    public static void main(String[] args) {
        try {
            FileInputStream inputStream =
                    new FileInputStream("/Users/huwenqi/Desktop/6.29-7.5结清未复借.xlsx");
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            System.out.println(
                    list.stream()
                            .map(single -> String.format("'+62%s'", single.get(1)))
                            .collect(Collectors.joining(",")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
