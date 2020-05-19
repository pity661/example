package com.wenky.example.io;

import java.io.File;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-26 14:53
 */
public class FilePath {
  public static String getPath(String fileName) {
    return ClassLoader.getSystemResource("static").getPath() + File.separator + fileName;
  }
}
