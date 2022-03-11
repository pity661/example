package com.wenky.example.io.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-25 11:37
 */
public class RandomAccessFilePosition {
    public static String fileName = "/Users/huwenqi/Desktop/random.txt";

    public static void main(String[] args) throws IOException {
        position();
    }

    private static void position() throws IOException {
        File file = new File(fileName);
        if (Boolean.FALSE == file.exists()) {
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rw");
        randomAccessFile.seek(1000);
        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println("file pose:" + fileChannel.position());
        randomAccessFile.seek(500);
        System.out.println("file pose:" + fileChannel.position());
        fileChannel.position(200);
        System.out.println("file pose:" + randomAccessFile.getFilePointer());

        ByteBuffer byteBuffer = ByteBuffer.wrap("我是猪".getBytes(StandardCharsets.UTF_8));
        fileChannel.write(byteBuffer);

        // flip后可以重复写入
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
    }

    public static void write() throws Exception {
        // 预分配文件所占的磁盘空间，磁盘中会创建一个指定大小的文件
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.setLength(1024 * 1024); // 预分配 1M 的文件空间
        raf.close();

        // 所要写入的文件内容
        String s1 = "第一个字符串";
        String s2 = "第二个字符串";
        String s3 = "第三个字符串";
        String s4 = "第四个字符串";
        String s5 = "第五个字符串";

        // 利用多线程同时写入一个文件
        new FileWriteThread(1024 * 1, s1.getBytes()).start(); // 从文件的1024字节之后开始写入数据
        new FileWriteThread(1024 * 2, s2.getBytes()).start(); // 从文件的2048字节之后开始写入数据
        new FileWriteThread(1024 * 3, s3.getBytes()).start(); // 从文件的3072字节之后开始写入数据
        new FileWriteThread(1024 * 4, s4.getBytes()).start(); // 从文件的4096字节之后开始写入数据
        new FileWriteThread(1024 * 5, s5.getBytes()).start(); // 从文件的5120字节之后开始写入数据
    }

    // 利用线程在文件的指定位置写入指定数据
    static class FileWriteThread extends Thread {
        private int skip;
        private byte[] content;

        public FileWriteThread(int skip, byte[] content) {
            this.skip = skip;
            this.content = content;
        }

        public void run() {
            RandomAccessFile raf = null;
            try {
                raf = new RandomAccessFile(fileName, "rw");
                raf.seek(skip);
                raf.write(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    raf.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
