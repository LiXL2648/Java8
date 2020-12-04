package com.li.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        new Thread(sellTicket, "一号窗口").start();
        new Thread(sellTicket, "二号窗口").start();
        new Thread(sellTicket, "三号窗口").start();
    }
}

class SellTicket implements Runnable {

    private int ticketNum = 100;

    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while (ticketNum > 0) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticketNum);
            } finally {
                lock.unlock();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
