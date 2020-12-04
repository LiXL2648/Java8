package com.li.juc.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class CopyOnWriterArrayListTest {

    public static void main(String[] args) {
        CopyOnWriterArrayThread thread = new CopyOnWriterArrayThread();

        for (int i = 0; i < 10; i++) {
            new Thread(thread).start();
        }
    }
}

class CopyOnWriterArrayThread implements Runnable {
    // private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    private static CopyOnWriteArrayList list = new CopyOnWriteArrayList<>();

    static {
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());

            list.add("DDDD");
        }
    }
}
