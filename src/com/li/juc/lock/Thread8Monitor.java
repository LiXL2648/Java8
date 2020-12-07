package com.li.juc.lock;

import org.junit.Test;

public class Thread8Monitor {

    public static void main(String[] args) {

        // 线程八锁
        Monitoreight monitoreight1 = new Monitoreight();
        Monitoreight monitoreight2 = new Monitoreight();
        new Thread(() -> monitoreight1.getOne()).start();
        new Thread(() -> monitoreight2.getTwo()).start();

        // 线程七锁
//        MonitorSeven monitorSeven1 = new MonitorSeven();
//        MonitorSeven monitorSeven2 = new MonitorSeven();
//        new Thread(() -> monitorSeven1.getOne()).start();
//        new Thread(() -> monitorSeven2.getTwo()).start();

        // 线程六锁
//        MonitorSix monitorSix = new MonitorSix();
//        new Thread(() -> monitorSix.getOne()).start();
//        new Thread(() -> monitorSix.getTwo()).start();

        // 线程五锁
//        MonitorFive monitorFive = new MonitorFive();
//        new Thread(() -> monitorFive.getOne()).start();
//        new Thread(() -> monitorFive.getTwo()).start();

        // 线程四锁
//        MonitorFour monitorFour1 = new MonitorFour();
//        MonitorFour monitorFour2 = new MonitorFour();
//        new Thread(() -> monitorFour1.getOne()).start();
//        new Thread(() -> monitorFour2.getTwo()).start();

        // 线程三锁
//        MonitorThree monitorThree = new MonitorThree();
//        new Thread(() -> monitorThree.getOne()).start();
//        new Thread(() -> monitorThree.getTwo()).start();
//        new Thread(() -> monitorThree.getThree()).start();

        // 线程二锁
//        MonitorTwo two = new MonitorTwo();
//        new Thread(() -> two.getOne()).start();
//        new Thread(() -> two.getTwo()).start();

        // 线程一锁
//        MonitorOne one = new MonitorOne();
//        new Thread(() -> one.getOne()).start();
//        new Thread(() -> one.getTwo()).start();
    }
}

class Monitoreight {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }
}

class MonitorSeven {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }
}

class MonitorSix {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public static synchronized void getTwo() {
        System.out.println("two");
    }
}

class MonitorFive {
    public static synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }
}

class MonitorFour {
    public synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }
}

class MonitorThree {

    public synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("three");
    }
}

class MonitorTwo {

    public synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }
}

class MonitorOne {

    public synchronized void getOne() {
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }
}
