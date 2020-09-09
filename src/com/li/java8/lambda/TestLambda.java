package com.li.java8.lambda;

import com.li.java8.domain.Employee;
import com.li.java8.interfact.impl.FilterEmployeeByAge;
import com.li.java8.interfact.MyPredicate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestLambda {

    List<Employee> list = Arrays.asList(
            new Employee(1, "LiXL", 1, 18, 8000.00),
            new Employee(2, "YuCX", 0, 19, 9000.00),
            new Employee(3, "LinK", 1, 17, 7000.00),
            new Employee(4, "LinZH", 1, 18, 8000.00),
            new Employee(4, "LiLX", 0, 16, 9000.00)
            );

    // 优化方式四：Stream API
    @Test
    public void test5() {
        list.stream().filter(employee -> employee.getGender() == 1)
                .forEach(System.out::println);
    }

    // 优化方式三：lambda表达式
    @Test
    public void test4() {
        List<Employee> emps = filterEmployeeByAge1(list, employee -> employee.getGender() == 0);
        emps.forEach(System.out::println);
    }

    // 优化方式二，匿名内部类
    @Test
    public void test3() {
        System.out.println(filterEmployeeByAge1(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() > 8000;
            }
        }));
    }

    // 优化方式一：策略设计模式
    @Test
    public void test2() {
        System.out.println(filterEmployeeByAge(list));

        System.out.println(filterEmployeeByAge1(list, new FilterEmployeeByAge()));
    }

    private List<Employee> filterEmployeeByAge1(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> emps = new ArrayList<>();

        for (Employee employee: list) {
            if (myPredicate.test(employee)) {
                emps.add(employee);
            }
        }
        return  emps;
    }

    // 传统方式：获取当前公司员工年龄小于18的员工信息
    private List<Employee> filterEmployeeByAge(List<Employee> list) {
        List<Employee> newList = new ArrayList<>();

        for (Employee employee: list) {
            if (employee.getAge() < 18) {
                newList.add(employee);
            }
        }
        return newList;
    }

    @Test
    public void test1() {

        // 匿名内部类
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, 02);
            }
        };

        // Lambda表达式
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);
    }
}
