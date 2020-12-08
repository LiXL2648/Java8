package com.li.nio.channel;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;

public class FileChannelTest {

    @Test
    public void testTransfer() {
        // 利用 transferTo()、transferFrom() 完成数据在通道间的传输，使用的是直接缓冲区

        FileChannel inFileChannel = null;
        FileChannel outFileChannel = null;
        try {
            LocalDateTime start = LocalDateTime.now();

            // 获取文件输入输出通道
            inFileChannel = FileChannel.open(Paths.get("1.sql"), StandardOpenOption.READ);
            outFileChannel = FileChannel.open(Paths.get("5.sql"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            // inFileChannel.transferTo(0, inFileChannel.size(), outFileChannel);
            outFileChannel.transferFrom(inFileChannel, 0, inFileChannel.size());
            LocalDateTime end = LocalDateTime.now();
            System.out.println("消耗的时间为：" + Duration.between(start, end).toMillis()); // 183 184
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outFileChannel != null) {
                try {
                    outFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inFileChannel != null) {
                try {
                    inFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileOpenChannel() {
        // 利用通道完成文件的复制（使用直接缓冲区，内存映射文件）

        FileChannel inFileChannel = null;
        FileChannel outFileChannel = null;
        try {
            LocalDateTime start = LocalDateTime.now();

            // 获取文件输入输出通道

            // 输入通道只需要进行读操作
            inFileChannel = FileChannel.open(Paths.get("1.sql"), StandardOpenOption.READ);
            // 输出通道由于输出缓冲区指定的模式为读写，因此需要指定读、写操作，并指定文件创建方式
            outFileChannel = FileChannel.open(Paths.get("3.sql"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            // 获取内存映射文件
            MappedByteBuffer inMappedByteBuffer = inFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inFileChannel.size());
            MappedByteBuffer outMappedByteBuffer = outFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, inFileChannel.size());

            // 直接在缓冲区进行数据的读写
            byte[] dst = new byte[inMappedByteBuffer.limit()];
            inMappedByteBuffer.get(dst);
            outMappedByteBuffer.put(dst);

            LocalDateTime end = LocalDateTime.now();
            System.out.println("消耗的时间为：" + Duration.between(start, end).toMillis()); // 6 375
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outFileChannel != null) {
                try {
                    outFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inFileChannel != null) {
                try {
                    inFileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileStreamChannel() {
        // 利用通道完成文件的复制（使用非直接缓冲区）

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            LocalDateTime start = LocalDateTime.now();

            // 获取文件的输入输出流
            fis = new FileInputStream("1.sql");
            fos = new FileOutputStream("2.sql");

            // 获取输入输出流对应的通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            // 获取非直接缓冲区，并指定大小
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 将通道中的数据存入缓冲区中
            while (inChannel.read(buffer) != -1) {
                // 切换读取数据模式
                buffer.flip();
                // 将缓冲区中的数据写入通道中
                outChannel.write(buffer);
                // 清空缓冲区
                buffer.flip();
            }

            LocalDateTime end = LocalDateTime.now();
            System.out.println("消耗的时间为：" + Duration.between(start, end).toMillis()); // 3 1418
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
