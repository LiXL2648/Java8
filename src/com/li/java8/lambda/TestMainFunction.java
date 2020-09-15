package com.li.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestMainFunction {

    // Predicate<T>：断言型接口
    @Test
    public void testPredicate() {
        List<String> list = Arrays.asList("LiXL", "LiXC", "LiXY", "LiLX", "YuCX");
        List<String> newList = filter(list, str -> str.startsWith("Li"));
        System.out.println(newList);
    }

    private List<String> filter(List<String> list, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();
        list.forEach(str -> {
            if (predicate.test(str)) {
                newList.add(str);
            }
        });
        return newList;
    }

    // Function<T, R>：函数型接口
    @Test
    public void testFunction() {
        String result = strHandler("LiXL", str -> str.toUpperCase());
        System.out.println(result);
    }

    private String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    //Supplier<T>：供给型接口
    @Test
    public void testSupplier() {
        List<Integer> list = supplie(10, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);
    }

    private List<Integer> supplie(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer value = supplier.get();
            list.add(value);
        }
        return list;
    }

    // Consumer<T>：消费型接口
    @Test
    public void testConsumer() {
        consume(100D, menoy -> System.out.println("其次消费总共花费 " + menoy + " 元"));
    }

    private void consume(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
