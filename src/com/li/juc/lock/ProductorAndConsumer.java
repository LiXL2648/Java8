package com.li.juc.lock;

public class ProductorAndConsumer {

    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(productor, "生产者 A").start();
        new Thread(consumer, "消费者 B").start();

        new Thread(productor, "生产者 C").start();
        new Thread(consumer, "消费者 D").start();
    }
}

class Clerk {
    private int product = 0;

    public synchronized void getProduct() {
        while (product >= 1) {
            System.out.println("产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ": " + ++product);
        this.notifyAll();
    }

    public synchronized void sale() {
        while (product <= 0) {
            System.out.println("产品已卖完");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ": " + --product);
        this.notifyAll();
    }
}

class Productor implements Runnable {

    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            clerk.getProduct();
        }
    }
}

class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
            clerk.sale();
        }
    }
}