package com.wenky.example.io.stream;

import com.wenky.example.io.FilePath;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-09 11:43
 */
public class StreamTest {
    // inputStreamHandle
    public static void inputStreamHandle() throws IOException {
        File targetFile = new File(FilePath.getPath("testfile"));
        try (InputStream inputStream = new FileInputStream("/Users/huwenqi/Desktop/testfile");
                ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
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

    public static void main(String[] args) throws IOException {
        inputStreamHandle();
    }
}
