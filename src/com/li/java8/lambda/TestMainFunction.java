package com.li.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;

public class TestMainFunction {


    // Consumer<T>：消费型接口
    @Test
    public void testConsumer() {
        consume(100D, menoy -> System.out.println());
    }

    public void consume(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
