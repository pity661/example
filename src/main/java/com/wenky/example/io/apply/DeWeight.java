package com.wenky.example.io.apply;

import com.wenky.example.io.FilePath;
import java.io.*;

/**
 * @program: example
 * @description: BufferedReader::readLine
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-26 18:27
 */
public class DeWeight {
  private static final Integer fileCount = 20;
  private static final String file = FilePath.getPath("111.txt");

  public static void separateLargeFiles() throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {}
    }
  }
}
