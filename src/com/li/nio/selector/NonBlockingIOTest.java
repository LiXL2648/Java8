package com.li.nio.selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

public class NonBlockingIOTest {

    @Test
    public void testServer() {

        SocketChannel socketChannel = null;
        try {
            // 获取 ServerSocketChannel 通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            // 将通道切换成非阻塞式
            serverSocketChannel.configureBlocking(false);

            // 绑定端口号，等待客户端连接
            serverSocketChannel.bind(new InetSocketAddress(2648));

            // 获取选择器
            Selector selector = Selector.open();

            // 将通道注册在选择器上，并指定“监听接收事件”
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 轮询式的获取选择器上已经“准备就绪”的事件
            while (selector.select() > 0) {

                // 获取当前选择器中所有注册的选择键（已就绪的监听事件）
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {

                    // 获取“准备就绪”的事件
                    SelectionKey selectionKey = selectionKeys.next();
                    if (selectionKey.isAcceptable()) {
                        // 如果是接收就绪状态通道，则获取客户端连接，接收客户端数据
                        socketChannel = serverSocketChannel.accept();

                        // 将客户端 SocketChannel 通道切换成非阻塞式
                        socketChannel.configureBlocking(false);

                        // 将客户端 SocketChannel 通道注册在 selector 选择器上，并监听该通道的读就绪
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()){
                        // 若是读就绪状态的通道，则获取该通道
                        socketChannel = (SocketChannel) selectionKey.channel();

                        // 将通道中的数据读取到缓冲区，并将缓冲区的数据读入
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        while (socketChannel.read(buffer) != -1) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, buffer.limit()));
                            buffer.clear();
                        }

                        // 发送反馈数据给客户端
                        /*buffer.put("接收客户端数据成功".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();*/
                    }

                    // 取消选择键，不然该选择键将一直有效
                    selectionKeys.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClient() {

        // 建立一个非阻塞式客户端
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {

            // 获取客户端 SocketChannel 通道
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 2648));

            // 切换成非阻塞式
            socketChannel.configureBlocking(false);

            // 获取缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 写入数据到缓冲区，并将缓冲区的数据写入 SocketChannel 通道中
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.next();
                buffer.put((LocalDateTime.now().toString() + "\n" + message).getBytes());
                buffer.flip();
                socketChannel.write(buffer);
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
