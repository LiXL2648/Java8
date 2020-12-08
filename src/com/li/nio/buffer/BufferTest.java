package com.li.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferTest {

    @Test
    public void testBuffer() {

        // 利用 ByteBuffer.allocate(20) 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(20);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(20);

        // 利用 isDirect() 判断缓冲区是直接缓冲区还是非直接缓冲区
        System.out.println("--------- isDirect(20) ----------");
        System.out.println(directBuffer.isDirect());

        System.out.println("--------- allocate(20) ----------");
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 20
        System.out.println(buffer.capacity()); // 20

        // 利用 put() 将数据存入 缓冲区中
        System.out.println("------------ put() --------------");
        String str = "LiXL";
        buffer.put(str.getBytes());
        System.out.println(buffer.position()); // 4
        System.out.println(buffer.limit()); // 20
        System.out.println(buffer.capacity()); // 20

        // 利用 flip() 切换读取数据模式
        System.out.println("------------ flip() --------------");
        buffer.flip();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 4
        System.out.println(buffer.capacity()); // 20

        // 利用 get() 将缓冲区中的数据读到 byte数组中
        System.out.println("------------ get() --------------");
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length)); //LiXL
        System.out.println(buffer.position()); // 4
        System.out.println(buffer.limit()); // 4
        System.out.println(buffer.capacity()); // 20

        // 利用 rewind()，可重复读取数据
        System.out.println("----------- rewind() -------------");
        buffer.rewind();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 4
        System.out.println(buffer.capacity()); // 20

        // 利用 mark() 标记 position 的位置
        System.out.println("----------- mark() -------------");
        buffer.get(bytes, 0, 2);
        System.out.println(new String(bytes, 0, 2)); // Li
        System.out.println(buffer.position()); // 2
        buffer.mark();

        // 利用 reset() 恢复 position 到 mark 的位置
        System.out.println("----------- reset() -------------");
        buffer.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2, 2)); // XL
        System.out.println(buffer.position()); // 4
        buffer.reset();
        System.out.println(buffer.position()); // 2

        // 利用 hasRemaining() 判断缓冲区中是否还有数据，remaining()获取缓冲区中数据的数量
        System.out.println("-- hasRemaining()、remaining() --");
        if (buffer.hasRemaining()) {
            System.out.println(buffer.remaining()); // 2
        }

        // 利用 clear() 清空缓冲区，重回些写状态，但是缓冲区中的数据依然存在，只是处于“被遗忘”状态
        System.out.println("----------- clear() -------------");
        buffer.clear();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 20
        System.out.println(buffer.capacity()); // 20
    }
}
