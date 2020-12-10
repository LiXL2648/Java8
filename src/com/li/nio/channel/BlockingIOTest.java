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

public class BlockingIOTest {

    @Test
    public void testServer() {
        // 使用 ServerSocketChannel 建立一个服务端通道，接收客户端的数据

        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            // 获取服务端 ServerSocketChannel 通道
            serverSocketChannel = ServerSocketChannel.open();

            // 绑定端口号，等待客户端连接
            serverSocketChannel.bind(new InetSocketAddress(2648));

            // 获取客户端连接 SocketChannel 通道
            socketChannel = serverSocketChannel.accept();

            // 获取本地本地文件 FileChannel 通道
            fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);

            // 获取缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 将客户端中的数据写入缓冲区
            while (socketChannel.read(buffer) != -1) {
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // 关闭通道
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
        // 使用 SocketChannel 建立一个客户端通道 给服务端发送数据

        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            // 获取 客户端 SocketChannel 通道
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2648));

            // 获取缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 获取本地文件 FileChannel 通道
            fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

            // 将本地文件通道 FileChannel 的数据写入缓冲区中
            while (fileChannel.read(buffer) != -1) {
                // 切换缓冲区为读模式
                buffer.flip();

                // 将缓冲区中的数据写入 SocketChannel 通道中
                socketChannel.write(buffer);

                // 清空缓冲区
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
