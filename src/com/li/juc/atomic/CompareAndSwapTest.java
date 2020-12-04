package com.li.juc.atomic;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

// 模拟 CAS 算法
public class CompareAndSwapTest {

    public static void main(String[] args) {
        CompareAndSwap compareAndSwap = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread(() -> {
                while (!compareAndSwap.compareAndSet(compareAndSwap.getValue(), j));
            }).start();
        }
    }
}

class CompareAndSwap {
    private int value = 0;

    // 获取内存值
    public synchronized int getValue() {
        return value;
    }

    // 比较和替换
    public synchronized int compareAndSwap(int expecte, int newValue) {
        // 先获取内存值
        int oldValue = this.getValue();

        if (expecte == oldValue) {
            this.value = newValue;
            System.out.println(Thread.currentThread().getName() + ": " + this.value);
        }

        return oldValue;
    }

    // 是否修改成功
    public synchronized boolean compareAndSet(int expecte, int newValue) {
        return expecte == this.compareAndSwap(expecte, newValue);
    }
}