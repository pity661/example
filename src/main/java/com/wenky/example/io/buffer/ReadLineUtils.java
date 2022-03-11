package com.wenky.example.io.buffer;

import com.wenky.example.io.FilePath;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-27 10:29
 */
public class ReadLineUtils implements Closeable {
    private FileChannel channel;
    private ByteBuffer inBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer outBuffer = ByteBuffer.allocate(16);

    /**
     * 调用readLine方法通过缓冲区的方式读取文件行内容
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    ReadLineUtils(String fileName) throws FileNotFoundException {
        this.channel = new FileInputStream(fileName).getChannel();
    }

    private String returnLine(ByteBuffer outBuffer) {
        outBuffer.flip();
        final String line = Charset.forName("utf-8").decode(outBuffer).toString();
        outBuffer.clear();
        return line;
    }

    private String readLine(ByteBuffer inBuffer) {
        while (inBuffer.hasRemaining()) {
            // 按单个字节读取，遇到10/13就是换行(把之前的行输出)
            byte b = inBuffer.get();
            if (b == 10 || b == 13) {
                outBuffer.put(b);
                // 输出outBuffer
                return returnLine(outBuffer);
            }
            if (!outBuffer.hasRemaining()) {
                outBuffer = resize(outBuffer);
            }
            outBuffer.put(b);
        }
        return null;
    }

    public String readLine() throws IOException {
        // 上一次读取数据的还有剩余
        if (inBuffer.hasRemaining() && inBuffer.position() != 0) {
            String value = readLine(inBuffer);
            if (value != null) {
                return value;
            }
            inBuffer.clear();
        }
        // 开始读取新的缓冲数据
        while (channel.read(inBuffer) != -1) {
            inBuffer.flip();
            String value = readLine(inBuffer);
            if (value == null) {
                inBuffer.clear();
                continue;
            }
            return value;
        }
        // 把最后一行输出(没有换行符的)
        if (outBuffer.hasRemaining()) {
            printOut(outBuffer);
        }
        return null;
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
        try (ReadLineUtils bufferReadLine = new ReadLineUtils(FilePath.getPath("read-line.txt"))) {
            String line;
            while ((line = bufferReadLine.readLine()) != null) {
                System.out.print(line);
            }
        }
    }

    // 在try中初始化对象，会自动执行这个方法
    @Override
    public void close() throws IOException {
        channel.close();
    }
}
