package com.wenky.example.io.buffer;

import com.wenky.example.io.FilePath;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-27 11:55
 */
public class ReadLineTest {
  /**
   * 缓冲区方式按行读取文件(一次性读取所有内容)
   *
   * @throws IOException
   */
  public static void readLineTest() throws IOException {
    ByteBuffer inBuffer = ByteBuffer.allocate(1024);
    ByteBuffer outBuffer = ByteBuffer.allocate(16);
    // RandomAccessFile 和 FileInputStream的区别可以指定未知读取文件
    try (FileChannel channel =
        new RandomAccessFile(FilePath.getPath("read-line.txt"), "rw").getChannel()) {
      int count;
      while ((count = channel.read(inBuffer)) != -1) {
        // System.out.println(count);
        inBuffer.flip();
        while (inBuffer.hasRemaining()) {
          // 按单个字节读取，遇到10/13就是换行(把之前的行输出)
          byte b = inBuffer.get();
          if (b == 10 || b == 13) {
            outBuffer.put(b);
            // 输出outBuffer
            printOut(outBuffer);
            continue;
          }
          // 扩容处理
          if (!outBuffer.hasRemaining()) {
            outBuffer = resize(outBuffer);
          }
          outBuffer.put(b);
        }
        // 把最后一行输出(没有换行符的)
        if (outBuffer.hasRemaining()) {
          printOut(outBuffer);
        }
        inBuffer.clear();
      }
    }
  }

  private static ByteBuffer resize(ByteBuffer outBuffer) {
    final int capacity = outBuffer.capacity();
    // outBuffer大小扩容为原来的2倍
    byte[] contain = new byte[capacity << 1];
    // 数组拷贝
    System.arraycopy(outBuffer.array(), 0, contain, 0, capacity);
    return (ByteBuffer) ByteBuffer.wrap(contain).position(capacity);
  }

  private static void printOut(ByteBuffer outBuffer) {
    outBuffer.flip();
    final String line = Charset.forName("utf-8").decode(outBuffer).toString();
    System.out.print(line);
    outBuffer.clear();
  }

  public static void main(String[] args) throws IOException {
    readLineTest();
  }
}
