package com.wenky.example.io.channel;

import com.wenky.example.io.FilePath;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-26 14:10
 */
public class ChannelTest {
  private static final String sourceFile = FilePath.getPath("channel.txt");
  private static final String targetFile = FilePath.getPath("channel1.txt");

  public static void getChannel() throws IOException {
    FileInputStream in = new FileInputStream("XXX");
    FileChannel readableChannel = in.getChannel();
    in.close();
    readableChannel.close();

    FileOutputStream out = new FileOutputStream("xxx");
    FileChannel writeableChannel = out.getChannel();
    out.close();
    writeableChannel.close();
  }

  public static void fileChannelCopy1() throws IOException {
    System.out.println(sourceFile);
    try (ReadableByteChannel readableByteChannel = new FileInputStream(sourceFile).getChannel()) {
      ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
      System.out.println(targetFile);
      try (WritableByteChannel writableByteChannel =
          new FileOutputStream(targetFile).getChannel()) {
        // channel - to - channel
        while (readableByteChannel.read(byteBuffer) > 0) {
          byteBuffer.flip();
          while (byteBuffer.hasRemaining()) {
            writableByteChannel.write(byteBuffer);
          }
          byteBuffer.clear();
        }
      }
    }
  }

  public static void channelTransfer() throws IOException {
    try (FileChannel channel =
        new FileOutputStream(FilePath.getPath("channel-a.txt")).getChannel()) {
      String[] files = new String[] {"channel-b.txt", "channel-c.txt"};
      for (int i = 0; i < files.length; i++) {
        try (FileChannel channel1 = new FileInputStream(FilePath.getPath(files[i])).getChannel()) {
          // 全文件数据拷贝
          channel1.transferTo(0, channel1.size(), channel);
          // 输出一个换行符
          ByteBuffer byteBuffer = ByteBuffer.allocate(1);
          // 换行
          byteBuffer.put("\r".getBytes()); // 13
          byteBuffer.put("\n".getBytes()); // 10
          System.out.println(byteBuffer.position());
          byteBuffer.flip();
          while (byteBuffer.hasRemaining()) {
            channel.write(byteBuffer);
          }
        }
      }
    }
  }

  public static void gatherTest() throws IOException {
    List<ByteBuffer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      ByteBuffer byteBuffer = ByteBuffer.allocate(1);
      byteBuffer.put(String.valueOf(i).getBytes());
      byteBuffer.flip();
      list.add(byteBuffer);
    }
    try (GatheringByteChannel channel =
        new FileOutputStream(FilePath.getPath("channel-gather.txt")).getChannel()) {
      channel.write(list.toArray(new ByteBuffer[list.size()]));
    }
  }

  /** @throws IOException */
  public static void scatterTest() throws IOException {
    try (ScatteringByteChannel channel =
        new FileInputStream(FilePath.getPath("channel-gather.txt")).getChannel()) {
      // 这里注意outChannel必须定义在read方法外面
      try (WritableByteChannel channel1 = Channels.newChannel(System.out)) {
        ByteBuffer[] byteBuffers = {ByteBuffer.allocate(5), ByteBuffer.allocate(2)};
        long number;
        // 当文件读取position的值达到了文件大小时，会返回文件尾条件值 -1
        while ((number = channel.read(byteBuffers)) != -1) {
          System.out.println("读取成功:" + number);
          Stream.of(byteBuffers)
              .forEach(
                  byteBuffer -> {
                    byteBuffer.flip();
                    try {
                      // 不能确定channel.write()能一次性写入buffer的所有数据
                      while (byteBuffer.hasRemaining()) {
                        channel1.write(byteBuffer);
                      }
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                    System.out.println();
                    byteBuffer.clear();
                  });
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    //    fileChannelCopy1();
    channelTransfer();
    //    gatherTest();
    //    scatterTest();
  }
}
