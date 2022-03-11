package com.wenky.example.utils.excel;

import java.io.FileInputStream;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-08 14:17
 */
public class BankCodeAnalysis2Sql {
    public static void main(String[] args) {
        //        marketBanks();
        //    configMappingPartner();
        //    jiuYingBanks();
        //    haibeiBanks();
        //      ksBanks();
        //    hrBanks();
        //    ccBanks();
        sxBanks();
    }

    private static void sxBanks() {
        try {
            FileInputStream inputStream =
                    new FileInputStream("/Users/huwenqi/Desktop/印尼贷超银行列表 0629.xls");
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            // 17，18
            list.stream()
                    .filter(single -> StringUtils.isNotBlank(single.get(17)))
                    .forEach(
                            single -> {
                                String s =
                                        "('shuxingIndonesia', 'bank', '"
                                                + "DC_"
                                                + single.get(0)
                                                + "', '"
                                                + single.get(17)
                                                + "','"
                                                + single.get(18)
                                                + "','normal'),";
                                System.out.println(s);
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ccBanks() {
        try {
            FileInputStream inputStream =
                    new FileInputStream("/Users/huwenqi/Desktop/印尼贷超银行列表 0604.xls");
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            // 15，16
            list.stream()
                    .filter(single -> StringUtils.isNotBlank(single.get(13)))
                    .forEach(
                            single -> {
                                String s =
                                        "('chichuIndonesia', 'bank', '"
                                                + "DC_"
                                                + single.get(0)
                                                + "', '"
                                                + single.get(15)
                                                + "','"
                                                + single.get(16)
                                                + "','normal'),";
                                System.out.println(s);
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hrBanks() {
        try {
            FileInputStream inputStream =
                    new FileInputStream("/Users/huwenqi/Desktop/印尼贷超银行列表 0601.xls");
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            // 13，14
            list.stream()
                    .filter(single -> StringUtils.isNotBlank(single.get(13)))
                    .forEach(
                            single -> {
                                String s =
                                        "('huirongIndonesia', 'bank', '"
                                                + "DC_"
                                                + single.get(0)
                                                + "', '"
                                                + single.get(13)
                                                + "','"
                                                + single.get(14)
                                                + "','normal'),";
                                System.out.println(s);
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void haibeiBanks() {
        try (FileInputStream inputStream =
                new FileInputStream("/Users/huwenqi/Desktop/泰国渠道银行信息0413.xlsx")) {
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            // 初始化新增内部银行编码
            try {
                list.stream()
                        .forEach(
                                single -> {
                                    String s =
                                            "('1', '+66', '"
                                                    + single.get(0)
                                                    + "', '"
                                                    + single.get(1)
                                                    + "','"
                                                    + single.get(1)
                                                    + "','"
                                                    + ("常用".equals(single.get(2))
                                                            ? "ACTIVE"
                                                            : "INACTIVE")
                                                    + "'),";
                                    System.out.println(s);
                                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 海贝
            try {
                list.stream()
                        .filter(single -> StringUtils.isNotBlank(single.get(3)))
                        .forEach(
                                single -> {
                                    String s =
                                            "('haibeiThailand', 'bank', '"
                                                    + single.get(0)
                                                    + "', '"
                                                    + single.get(3)
                                                    + "','"
                                                    + single.get(4)
                                                    + "','normal'),";
                                    System.out.println(s);
                                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 熊猫
            try {
                list.stream()
                        .filter(single -> StringUtils.isNotBlank(single.get(5)))
                        .forEach(
                                single -> {
                                    String s =
                                            "('pandaThailand', 'bank', '"
                                                    + single.get(0)
                                                    + "', '"
                                                    + single.get(5)
                                                    + "','"
                                                    + single.get(6)
                                                    + "','normal'),";
                                    System.out.println(s);
                                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ksBanks() {
        try (FileInputStream inputStream =
                new FileInputStream("/Users/huwenqi/Desktop/印尼贷超银行列表 0426.xls")) {
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            // jiuYing
            try {
                list.stream()
                        .filter(single -> StringUtils.isNotBlank(single.get(11)))
                        .forEach(
                                single -> {
                                    String s =
                                            "('ksIndonesia', 'bank', '"
                                                    + "DC_"
                                                    + single.get(0)
                                                    + "', '"
                                                    + single.get(11)
                                                    + "','"
                                                    + single.get(12)
                                                    + "','normal'),";
                                    System.out.println(s);
                                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jiuYingBanks() {
        try (FileInputStream inputStream =
                new FileInputStream("/Users/huwenqi/Desktop/印尼贷超银行列表 0402.xls")) {
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            // 初始化新增内部银行编码
            try {
                list.stream()
                        .filter(
                                single ->
                                        StringUtils.isBlank(single.get(3))
                                                && StringUtils.isBlank(single.get(5))
                                                && StringUtils.isBlank(single.get(7)))
                        .forEach(
                                single -> {
                                    String s =
                                            "('1', '+62', '"
                                                    + "DC_"
                                                    + single.get(0)
                                                    + "', '"
                                                    + single.get(1)
                                                    + "','"
                                                    + single.get(1)
                                                    + "','"
                                                    + ("是".equals(single.get(2))
                                                            ? "ACTIVE"
                                                            : "INACTIVE")
                                                    + "'),";
                                    System.out.println(s);
                                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            // jiuYing
            try {
                list.stream()
                        .filter(single -> StringUtils.isNotBlank(single.get(9)))
                        .forEach(
                                single -> {
                                    String s =
                                            "('jiuyingIndonesia', 'bank', '"
                                                    + "DC_"
                                                    + single.get(0)
                                                    + "', '"
                                                    + single.get(9)
                                                    + "','"
                                                    + single.get(10)
                                                    + "','normal'),";
                                    System.out.println(s);
                                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void marketBanks() {
        try {
            FileInputStream inputStream =
                    new FileInputStream("/Users/huwenqi/Desktop/印尼贷超银行列表.xls");
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            list.stream()
                    .forEach(
                            single -> {
                                String s =
                                        "('1', '+62', '"
                                                + single.get(0)
                                                + "', '"
                                                + single.get(1)
                                                + "','"
                                                + single.get(1)
                                                + "','"
                                                + ("是".equals(single.get(2))
                                                        ? "ACTIVE"
                                                        : "INACTIVE")
                                                + "'),";
                                System.out.println(s);
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void configMappingPartner() {
        try {
            FileInputStream inputStream =
                    new FileInputStream("/Users/huwenqi/Desktop/印尼贷超银行列表.xls");
            // HSSFWorkbook 一次性将整个excel读入内存
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<List<String>> list = FileRead.readSheet(sheet);
            System.out.println(list.size());
            list = list.subList(0, list.size());
            // 3，4
            list.stream()
                    .filter(single -> StringUtils.isNotBlank(single.get(3)))
                    .forEach(
                            single -> {
                                String s =
                                        "('pandaIndonesia', 'bank', '"
                                                + single.get(0)
                                                + "', '"
                                                + single.get(3)
                                                + "','"
                                                + single.get(4)
                                                + "','normal'),";
                                System.out.println(s);
                            });
            // 5，6
            list.stream()
                    .filter(single -> StringUtils.isNotBlank(single.get(5)))
                    .forEach(
                            single -> {
                                String s =
                                        "('digitalloan', 'bank', '"
                                                + single.get(0)
                                                + "', '"
                                                + single.get(5)
                                                + "','"
                                                + single.get(6)
                                                + "','normal'),";
                                System.out.println(s);
                            });
            // 7，8
            list.stream()
                    .filter(single -> StringUtils.isNotBlank(single.get(7)))
                    .forEach(
                            single -> {
                                String s =
                                        "('xinghe', 'bank', '"
                                                + single.get(0)
                                                + "', '"
                                                + single.get(7)
                                                + "','"
                                                + single.get(8)
                                                + "','normal'),";
                                System.out.println(s);
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
