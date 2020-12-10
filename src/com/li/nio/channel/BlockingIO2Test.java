package com.li.nio.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockingIO2Test {

    @Test
    public void testServer() {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(2648));
            socketChannel = serverSocketChannel.accept();
            fileChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.READ,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }
            // 发送反馈数据给客户端
            buffer.put("服务端接收数据成功".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (serverSocketChannel != null) {
                try {
                    serverSocketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testClient() {

        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2648));
            fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }

            // 告诉服务端，数据已经发送完毕
            socketChannel.shutdownOutput();

            // 接收服务端的反馈
            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                System.out.println(new String(buffer.array(), 0, buffer.limit()));
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
