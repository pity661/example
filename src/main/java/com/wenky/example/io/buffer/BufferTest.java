package com.wenky.example.io.buffer;

import com.wenky.example.io.FilePath;
import java.io.*;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-26 14:52
 */
public class BufferTest {

    public static void bufferedWriter() throws IOException {
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(FilePath.getPath("buffer.txt")))) {
            writer.newLine();
            String value = "123";
            writer.write(value, 0, value.length());
            writer.newLine();
            writer.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        bufferedWriter();
    }
}
