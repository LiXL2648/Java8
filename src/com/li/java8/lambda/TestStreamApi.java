package com.li.java8.lambda;

import com.li.java8.domain.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 的三个操作步骤
 *      ① 创建Stream：一个数据源（如：集合，数组），获取一个流
 *      ② 中间操作：一个中间操作链，对数据源的数据进行处理
 *      ③ 中止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果
 */
public class TestStreamApi {

    // 中间操作
    public void testStreamApi() {
        // 1.
    }

    // 创建Stream
    @Test
    public void testStream() {
        // 1. 通过Collection系列集合提供的Stream()或者parallel()
        List<Employee> emps = new ArrayList<>();
        Stream<Employee> stream = emps.stream();
        Stream<Employee> parallelStream = emps.parallelStream();

        // 2. 通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        // 3. 通过Stream类中的静态方法of()
        Stream<String> stream2 = Stream.of("LiXL", "LiLX");

        // 4. 创建无限流

        // ① 迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        // ② 生成
        Stream<Integer> stream4 = Stream.generate(() -> (int) (Math.random() * 10000));
        stream4.limit(10).forEach(System.out::println);
    }
}
