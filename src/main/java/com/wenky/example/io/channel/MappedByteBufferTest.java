package com.wenky.example.io.channel;

import com.wenky.example.io.FilePath;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-03-30 19:49
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        //        read();
        write();
    }

    public static void read() throws IOException {
        try (FileChannel fileChannel =
                FileChannel.open(Paths.get(FilePath.getPath("mapped.txt")))) {
            // mode 文件映射模式
            //            FileChannel.MapMode.READ_ONLY;    只读
            //            FileChannel.MapMode.READ_WRITE;   读/写
            //            FileChannel.MapMode.PRIVATE;  私有，对缓冲区的修改不会写入文件
            MappedByteBuffer mappedByteBuffer =
                    fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            if (mappedByteBuffer != null) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while (mappedByteBuffer.hasRemaining()) {
                    byteBuffer.put(mappedByteBuffer);
                    byteBuffer.flip();
                    System.out.println(Charset.forName("utf-8").decode(byteBuffer).toString());
                    byteBuffer.clear();
                }
            }
        }
    }

    public static void write() throws IOException {
        try (FileChannel fileChannel =
                FileChannel.open(
                        Paths.get(FilePath.getPath("mapped-write.txt")),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.READ,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.TRUNCATE_EXISTING)) {
            String value = "我是一只程序猿";
            //        CharBuffer charBuffer = CharBuffer.wrap(value);
            //        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
            ByteBuffer byteBuffer = ByteBuffer.wrap(value.getBytes("utf-8"));
            System.out.println(byteBuffer.capacity());
            MappedByteBuffer mappedByteBuffer =
                    fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, byteBuffer.capacity());
            if (mappedByteBuffer.hasRemaining()) {
                mappedByteBuffer.put(byteBuffer);
            }
        }
    }
}
