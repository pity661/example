package com.wenky.example.io.pipe;

import com.wenky.example.io.FilePath;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-25 17:31
 */
public class PipeTest {

  public static void stringExample() throws IOException {
    Pipe pipe = Pipe.open();
    new Thread(
            () -> {
              // write
              try (Pipe.SinkChannel sinkChannel = pipe.sink()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                for (int i = 0; i < 6; i++) {
                  String msg = "第" + i + "条消息";
                  System.out.println(Thread.currentThread().getName() + "发送" + msg);
                  buffer.put(msg.getBytes());
                  // 翻转buffer让sinkChannel能读取
                  buffer.flip();
                  while (buffer.hasRemaining()) {
                    sinkChannel.write(buffer);
                  }
                  // 清空buffer准备下一次写入
                  buffer.clear();
                }
                System.out.println(Thread.currentThread().getName() + "结束了");
              } catch (Exception e) {
                e.printStackTrace();
              }
            },
            "A")
        .start();

    new Thread(
            () -> {
              try (Pipe.SourceChannel sourceChannel = pipe.source()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int len;
                // 读取数据的时候若管道内数据为空
                // 执行到IOUtil.readIntoNativeBuffer 237  DatagramDispatcher.read0 39
                // 调用本地方法线程将被阻塞了 (用try的方式不会被阻塞?)

                // 当管道输入方Pipe.SinkChannel 被关闭时，方法将不再被阻塞
                while ((len = sourceChannel.read(buffer)) > 0) {
                  buffer.flip();
                  System.out.println(
                      Thread.currentThread().getName()
                          + "接收到："
                          + new String(buffer.array(), 0, len));
                  buffer.clear();
                }
                System.out.println(Thread.currentThread().getName() + "结束了");
              } catch (Exception e) {
                e.printStackTrace();
              }
            },
            "B")
        .start();
  }

  /** 通过管道双线程处理文件复制 */
  public static void fileExample() throws IOException {
    Pipe pipe = Pipe.open();
    new Thread(
            () -> {
              // write
              try (Pipe.SinkChannel sinkChannel = pipe.sink()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                String filePath = FilePath.getPath("pipe.txt");
                System.out.println(filePath);
                try (FileChannel fileChannel = new FileInputStream(filePath).getChannel()) {
                  while (fileChannel.read(buffer) > 0) {
                    // 翻转buffer让sinkChannel能读取
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                      sinkChannel.write(buffer);
                    }
                    // 清空buffer准备下一次写入
                    buffer.clear();
                  }
                }
                System.out.println(Thread.currentThread().getName() + "结束了");
              } catch (Exception e) {
                e.printStackTrace();
              }
            },
            "A")
        .start();

    new Thread(
            () -> {
              // read
              try (Pipe.SourceChannel sourceChannel = pipe.source()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                String filePath = FilePath.getPath("pipe1.txt");
                System.out.println(filePath);
                try (FileChannel fileChannel = new FileOutputStream(filePath).getChannel()) {
                  while ((sourceChannel.read(buffer)) > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                      fileChannel.write(buffer);
                    }
                    buffer.clear();
                  }
                  System.out.println(Thread.currentThread().getName() + "结束了");
                }
              } catch (Exception e) {
                e.printStackTrace();
              }
            },
            "B")
        .start();
  }

  public void test() throws IOException {
    PipedWriter pipedWriter = new PipedWriter();
    PipedReader pipedReader = new PipedReader(pipedWriter);
  }

  public static void main(String[] args) throws IOException {
    //    stringExample();
    fileExample();
  }
}
