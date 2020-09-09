package com.li.java8.lambda;

import com.li.java8.domain.Employee;
import com.li.java8.interfact.MyFunction;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TestLambdaExp {

    List<Employee> list = Arrays.asList(
            new Employee(1, "LiXL", 1, 18, 8000.00),
            new Employee(2, "YuCX", 0, 19, 9000.00),
            new Employee(3, "LinK", 1, 17, 7000.00),
            new Employee(4, "LinZH", 1, 18, 8000.00),
            new Employee(4, "LiLX", 0, 16, 9000.00)
    );

    @Test
    public void test4() {
        MyFunction<Integer> myFunction = x -> x * x;
        System.out.println( myFunction.operator(10));
    }

    /*
        Lambda表达式三：
        有两个或者以上的参数，有返回值，Lamnda中有多条语句
        (T t1, T t2) -> {}
        对应的函数式接口为：Comparator
     */
    @Test
    public void test3() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator.compare(1, 2));

        Comparator<Double> comparator1 = (d1, d2) -> {
            System.out.println("Comparator函数式接口");
            return Double.compare(d1, d2);
        };

        System.out.println(comparator1.compare(1D, 2D));
    }


    /*
        Lambda表达式二：
        有参数，无返回值
        (T t) -> {}
        对应的函数式接口为：Consumer
     */
    @Test
    public void test2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };
        consumer.accept("Hello");

        Consumer<String> consumer1 = str -> System.out.println(str);
        consumer1.accept("World");
    }

    /*
        Lambda表达式一：
        无参数，无返回值
        () - {}
        对应的函数式接口为：Runnable
     */
    @Test
    public void test1() {
        int i = 1;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello" + i);
            }
        };
        runnable.run();

        Runnable runnable1 = () -> System.out.println("Hello" + i);
        runnable1.run();
    }
}
