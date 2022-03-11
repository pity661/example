package com.wenky.example.utils.excel.olading;

import com.wenky.example.utils.excel.FileRead;
import java.io.FileInputStream;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-01-27 11:19
 */
public class City2Sql {
    public static void main(String[] args) {
        city2Sql();
    }

    public static void city2Sql() {
        try (FileInputStream inputStream =
                new FileInputStream("/Users/huwenqi/Desktop/待处理的数据.xlsx")) {
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list.stream()
                    .filter(city -> Integer.valueOf(city.get(4)) != 4)
                    .forEach(
                            city -> {
                                String sql =
                                        "('"
                                                + city.get(1)
                                                + "', '"
                                                + handleCode(city.get(0))
                                                + "', 0),";
                                System.out.println(sql);
                            });
            //   insert into t_city (type, name, code, parent_id, create_time, update_time)
            //   values ();
            //            list.stream()
            //                    .forEach(
            //                            single -> {
            //                                String s =
            //                                        "(0, 0, '"
            //                                                + single.get(1)
            //                                                + "', '"
            //                                                + single.get(0)
            //                                                + "','"
            //                                                + single.get(2)
            //                                                + "','"
            //                                                + single.get(3)
            //                                                + "',"
            //                                                + "1, true),";
            //                                System.out.println(s);
            //                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param code CHN013001001
     * @return CHN0130101
     */
    public static String handleCode(String code) {
        String subCode1 = code.substring(6, 7);
        String subCode2 = code.substring(9, 10);
        if (ObjectUtils.notEqual(subCode1, "0") || ObjectUtils.notEqual(subCode2, "0")) {
            System.out.println("不为0：" + code);
        }
        return code.substring(0, 6) + code.substring(7, 9) + code.substring(10);
    }
}
