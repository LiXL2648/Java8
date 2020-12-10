package com.li.nio.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.time.LocalDateTime;
import java.util.Iterator;

public class DatagramChannelTest {

    @Test
    public void testReceive() {
        try {

            // 获取接收端 DatagramChannel 通道
            DatagramChannel datagramChannel = DatagramChannel.open();

            // 切换 DatagramChannel 通道为非阻塞式
            datagramChannel.configureBlocking(false);

            // 绑定端口号等待客户端的连接
            datagramChannel.bind(new InetSocketAddress(2648));

            // 获取选择器
            Selector selector = Selector.open();

            // 将通道注册在选择器上，并指定选择器为监听通道的读状态
            datagramChannel.register(selector, SelectionKey.OP_READ);

            // 轮询式的获取选择器上已经“准备就绪”的事件
            while (selector.select() > 0) {

                // 获取所有已经“准备就绪”的选择键
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {

                    SelectionKey selectionKey = selectionKeys.next();
                    if (selectionKey.isReadable()) {

                        // 如果是读就绪状态，读取 DatagramChannel 通道的数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        datagramChannel.receive(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit()));
                        buffer.clear();
                    }

                    // 移除选择键
                    selectionKeys.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSend() {
        DatagramChannel datagramChannel = null;
        try {
            // 获取 发送端 DatagramChannel 通道
            datagramChannel = DatagramChannel.open();

            // 切换 DatagramChannel 通道为非阻塞模式
            datagramChannel.configureBlocking(false);

            // 获取缓冲区，并往缓冲区中写入数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put((LocalDateTime.now().toString() + "\n" + "Hello").getBytes());
            buffer.flip();

            // 使用 DatagramChannel 通道发送数据
            datagramChannel.send(buffer, new InetSocketAddress("127.0.0.1", 2648));
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramChannel != null) {
                try {
                    datagramChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
