package com.li.juc.volatileTest;

public class VolatileTest {

    public static void main(String[] args) {
        ThreadTask threadTask = new ThreadTask();
        new Thread(threadTask).start();

        while (true) {
            // synchronized (threadTask) {
            if (threadTask.isFlag()) {
                System.out.println("Hello World");
                break;
            }
            // }
        }
    }

}

class ThreadTask implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }

        flag = true;
        System.out.println("flag = " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}