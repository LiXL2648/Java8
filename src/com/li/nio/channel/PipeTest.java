package com.li.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {

    public static void main(String[] args) {
        try {
            PipeThread pipeThread = new PipeThread(Pipe.open());
            new Thread(() -> pipeThread.sink()).start();
            new Thread(() -> pipeThread.source()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PipeThread {

    private Pipe pipe;

    public PipeThread(Pipe pipe) {
        this.pipe = pipe;
    }

    public synchronized void source() {

        Pipe.SourceChannel sourceChannel = null;
        try {
            // 获取 SourceChannel 通道，用于接收数据
            sourceChannel = pipe.source();

            // 获取缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 将 SourceChannel 通道中的数据读到缓冲区中
            sourceChannel.read(buffer);
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.limit()));
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sourceChannel != null) {
                try {
                    sourceChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void sink() {

        Pipe.SinkChannel sinkChannel = null;
        try {
            // 获取 SinkChannel 通道，用于发送数据
            sinkChannel = pipe.sink();

            // 获取缓冲区，并在缓冲区中写入数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("通过单向管道发送数据".getBytes());
            buffer.flip();

            // 将缓冲区的数据写入 SinkChannel 通道
            sinkChannel.write(buffer);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sinkChannel != null) {
                try {
                    sinkChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
