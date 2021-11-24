package com.wenky.example.io.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-16 16:02
 */
public class ChannelCopy {

  public static void main(String[] args) throws IOException {
    ReadableByteChannel source = Channels.newChannel(System.in);
    WritableByteChannel dest = Channels.newChannel(System.out);

    channelCopy1(source, dest);

    source.close();
    dest.close();
  }

  /**
   * 一次性读完然后输出
   *
   * @param source
   * @param dest
   * @throws IOException
   */
  private static void channelCopy1(ReadableByteChannel source, WritableByteChannel dest)
      throws IOException {
    // 系统级内存分配，省去复制操作，提高性能 但是比allocate耗时
    ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
    while (source.read(buffer) != -1) {
      // read的时候已经往缓冲区写入数据了
      // limit变为position既翻转缓冲区，
      // 准备从缓冲区读取数据写入到dest
      buffer.flip();
      // 写入数据到通道
      dest.write(buffer);
      // 区别于clear
      // 如果buffer中仍有数据，将position置于最后一个元素后，后续仍旧可以读取未读数据
      buffer.compact();
      // 直接将position置为0，清空缓冲区，准备读取数据
      // buffer.clear();
    }
    // 避免dest write的时候，没有完全读完缓存区的数据
    // System.out 会全部读完
    // 翻转，准备读数据
    buffer.flip();
    // 确保缓存区的数据全部被读出
    while (buffer.hasRemaining()) {
      dest.write(buffer);
    }
  }

  /**
   * 一边读一边输出
   *
   * @param source
   * @param dest
   * @throws IOException
   */
  private static void channelCopy2(ReadableByteChannel source, WritableByteChannel dest)
      throws IOException {
    // 硬盘块大小为512，所以缓冲区的大小最好设置为2的幂次方，方便读取处理数据
    ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
    while (source.read(buffer) != -1) {
      // 翻转准备读出数据
      buffer.flip();
      while (buffer.hasRemaining()) {
        dest.write(buffer);
      }
      // 翻转准备写入数据
      buffer.clear();
    }
  }
}
