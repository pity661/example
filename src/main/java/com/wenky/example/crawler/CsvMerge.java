package com.wenky.example.crawler;

import com.wenky.example.io.FilePath;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-08-05 16:31
 */
@Component
public class CsvMerge {

  public void mergeFile() throws ParseException, IOException {
    String dateStart = "2020-01-01";
    String dateEnd = "2020-05-01";
    Date start = DateUtils.parseDate(dateStart, "yyyy-MM-dd");
    Date end = DateUtils.parseDate(dateEnd, "yyyy-MM-dd");
    Set<String> allMobileSet = new HashSet<>();
    String line;
    BufferedReader reader;
    while (start.before(end)) {
      String targetFile = "/Users/huwenqi/Desktop/csv/";
      String fileName = targetFile + DateFormatUtils.format(start, "yyyy-MM-dd") + ".csv";
      System.out.println(fileName);
      try {
        reader = new BufferedReader(new FileReader(fileName));
      } catch (Exception e) {
        e.printStackTrace();
        start = DateUtils.addDays(start, 1);
        continue;
      }
      System.out.println("start: " + allMobileSet.size());
      while ((line = reader.readLine()) != null) {
        allMobileSet.add(line);
      }
      System.out.println("end: " + allMobileSet.size());
      start = DateUtils.addDays(start, 1);
    }
    try (BufferedWriter writer =
        new BufferedWriter(new FileWriter(FilePath.getPath("merge.csv"), true))) {
      allMobileSet.stream()
          .forEach(
              mobile -> {
                try {
                  writer.write(mobile);
                  writer.newLine();
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
      writer.flush();
    }
  }
}
