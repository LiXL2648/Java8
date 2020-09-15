package com.li.java8.lambda;

import com.li.java8.domain.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// 方法引用，构造器引用，数组引用
public class TestMethodRef {

    List<Employee> list = Arrays.asList(
            new Employee(1, "LiXL", 1, 18, 8000.00),
            new Employee(2, "YuCX", 0, 19, 9000.00),
            new Employee(3, "LinK", 1, 17, 7000.00),
            new Employee(4, "LinZH", 1, 18, 8000.00),
            new Employee(4, "LiLX", 0, 16, 9000.00)
    );

    // 数组引用：Type::new
    @Test
    public void test6() {
        Function<Integer, String[]> fun = x -> new String[x];
        System.out.println(fun.apply(10).length);

        Function<Integer, String[]> fun1 = String[]::new;
        System.out.println(fun1.apply(20).length);
    }

    // 构造器引用：类名::new
    @Test
    public void  test4() {
        Supplier<Employee> supplier = () -> new Employee();
        System.out.println(supplier.get());

        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());
    }

    @Test
    public void test5() {
        Function<String, Employee> fun = x -> new Employee(x);
        System.out.println(fun.apply("LiXL"));

        Function<String, Employee> fun1 = Employee::new;
        System.out.println(fun1.apply("LiLX"));
    }

    // 方法引用：类::实例方法名
    @Test
    public void test3() {
        BiPredicate<String, String> bp = (x, y) -> x.equalsIgnoreCase(y);
        System.out.println(bp.test("LiXL", "lixl"));

        BiPredicate<String, String> bp1 = String::equals;
        System.out.println(bp1.test("LiXL", "LiLX"));
    }

    // 方法引用：类::静态方法名
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        int compare = com.compare(48, 26);

        Comparator<Double> com1 = Double::compare;
        int compare1 = com1.compare(26D, 48D);
    }

    // 方法引用：对象::实例方法名
    @Test
    public void test1() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("Hello");

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("Hello World!");

        Employee employee = list.get(0);
        Supplier<String> supplier = employee::getEmpName;
        System.out.println(supplier.get());
    }
}
