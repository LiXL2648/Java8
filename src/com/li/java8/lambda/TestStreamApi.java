package com.li.java8.lambda;

import com.li.java8.domain.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 的三个操作步骤
 *      ① 创建Stream：一个数据源（如：集合，数组），获取一个流
 *      ② 中间操作：一个中间操作链，对数据源的数据进行处理
 *      ③ 中止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果
 */
public class TestStreamApi {

    List<Employee> list = Arrays.asList(
            new Employee(1, "LiXL", 1, 18, 8000.00),
            new Employee(2, "YuCX", 0, 19, 9000.00),
            new Employee(3, "LinK", 1, 17, 7000.00),
            new Employee(4, "LinZH", 1, 18, 8000.00),
            new Employee(5, "LiLX", 0, 16, 9000.00),
            new Employee(5, "LiLX", 0, 16, 9000.00),
            new Employee(5, "LiLX", 0, 16, 9000.00)
    );

    // 中间操作

    // 1. filter：排除某些元素
    @Test
    public void testFilter() {
        list.stream().filter(e -> e.getGender() == 1)
                .forEach(System.out::println);

        Iterator<Employee> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // 2. distinct：去除重复元素
    @Test
    public void testDistinct() {
        list.stream()
                .filter(e -> e.getSalary() > 7000)
                .distinct()
                .forEach(System.out::println);
    }

    // 3. limit：截断流，使其元素不超过给定的数量
    @Test
    public void testLimit() {
        list.stream()
                .filter(e -> e.getSalary() > 7000)
                .limit(2)
                .forEach(System.out::println);
    }

    // 4. skip：扔掉前 n 个元素的流
    @Test
    public void testSkip() {
        list.stream()
                .filter(e -> e.getSalary() > 7000)
                .skip(2)
                .forEach(System.out::println);
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
