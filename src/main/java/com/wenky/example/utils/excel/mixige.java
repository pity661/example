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
 * @create: 2021-06-23 16:32
 */
public class mixige {
    public static void main(String[] args) {
        //        sheet0();

        sheet1();
    }

    private static void sheet0() {
        String formatString =
                "insert into dictionary.i18n_values(created_at,updated_at,version,dictionary_id,value,language,\"order\") "
                        + "select now(), now(), 1, pkaid, '%s', 'es-MX', 0 from dictionary.dictionaries "
                        + "where category_code='%s' and code='%s';";
        try (FileInputStream inputStream =
                new FileInputStream("/Users/huwenqi/Desktop/墨西哥贷超码表.xlsx")) {
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list.stream()
                    .forEach(
                            single -> {
                                String s =
                                        String.format(
                                                formatString,
                                                single.get(6),
                                                single.get(1),
                                                single.get(3));
                                System.out.println(s);
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sheet1() {
        String formatString1 = "(now(),now(), 1, 0, '%s', '%s', '%s', '%s', 0, false),";
        String formatString2 =
                "insert into dictionary.i18n_values(created_at,updated_at,version,dictionary_id,value,language,\"order\") "
                        + "select now(), now(), 1, pkaid, '%s', 'es-MX', 0 from dictionary.dictionaries "
                        + "where category_code='%s' and code='%s';";
        try (FileInputStream inputStream =
                new FileInputStream("/Users/huwenqi/Desktop/墨西哥贷超码表.xlsx")) {
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(1);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list.stream()
                    .forEach(
                            single -> {
                                String s =
                                        String.format(
                                                formatString1,
                                                single.get(2),
                                                single.get(3),
                                                single.get(0),
                                                single.get(1));
                                System.out.println(s);
                            });
            list.stream()
                    .forEach(
                            single -> {
                                String s =
                                        String.format(
                                                formatString2,
                                                single.get(6),
                                                single.get(0),
                                                single.get(2));
                                System.out.println(s);
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
