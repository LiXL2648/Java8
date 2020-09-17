package com.li.java8.lambda;

import com.li.java8.domain.Employee;
import com.li.java8.domain.Trader;
import com.li.java8.domain.Transaction;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 的三个操作步骤
 *      ① 创建Stream：一个数据源（如：集合，数组），获取一个流
 *      ② 中间操作：一个中间操作链，对数据源的数据进行处理
 *      ③ 中止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果
 */
public class TestStreamApi {

    List<Employee> list = Arrays.asList(
            new Employee(1, "LiXL", 1, 18, 8000.00, Employee.Status.BUSY),
            new Employee(2, "YuCX", 0, 19, 9000.00, Employee.Status.FREE),
            new Employee(3, "LinK", 1, 17, 7000.00, Employee.Status.VOCATION),
            new Employee(4, "LinZH", 1, 18, 8000.00, Employee.Status.BUSY),
            new Employee(5, "LiLX", 0, 16, 9000.00, Employee.Status.FREE),
            new Employee(5, "LiLX", 0, 16, 9000.00, Employee.Status.FREE),
            new Employee(5, "LiLX", 0, 16, 9000.00, Employee.Status.FREE)
    );

    // 练习
    // 练习一：给定一个数组列表，如何返回一个由每个元素的平方构成的列表，给定[1, 2, 3, 4]，返回[1, 4, 9, 16]
    @Test
    public void testExer1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        list.stream()
                .map(i -> i * i)
                .forEach(System.out::println);
    }

    // 练习二：怎么使用map和reduce计算流中的Employee个数
    @Test
    public void testExer2() {
        Integer reduce = list.stream()
                .map(employee -> 1)
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    // 练习三：
    List<Transaction> transactions = Arrays.asList(
            new Transaction(new Trader("LiXL", "广州"), 1996, 48),
            new Transaction(new Trader("YuCX", "广州"), 1995, 29),
            new Transaction(new Trader("LiLX", "东莞"), 1996, 30),
            new Transaction(new Trader("LinK", "广州"), 1996, 18),
            new Transaction(new Trader("LinZH", "广州"), 1996, 20),
            new Transaction(new Trader("LiXC", "深圳"), 1992, 27)
    );

    // 找出1996年发生的所有交易，并按交易额排序（从低到高）
    @Test
    public void testExer3_1() {
        transactions.stream()
                .filter(t -> t.getYear() == 1996)
                .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()))
                .forEach(System.out::println);
    }

    // 交易员都在哪些不同的城市工作过
    @Test
    public void testExer3_2() {
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }

    //查找所有来自广州的交易员，并按照姓名排序
    @Test
    public void testExer3_3() {
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("广州"))
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .forEach(System.out::println);
    }
    // 返回所有交易员姓名的字符串，按字母顺序排序

    // 有没有交易员在东莞工作的

    // 打印生活在广州的交易员的所有交易额

    // 所有交易中，最高的交易额是多少

    // 找到交易额最小的交易。


    // 终止操作
    // 收集：

    // 收集到List中
    @Test
    public void testToList() {
        List<String> names = list.stream()
                .map(Employee::getEmpName)
                .distinct()
                .collect(Collectors.toList());
        names.forEach(System.out::println);
    }

    // 收集到set中
    @Test
    public void testToSet() {
        Set<String> set = list.stream()
                .map(Employee::getEmpName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
    }

    // 收集到一些特殊的数据结构
    @Test
    public void testToCollection() {
        HashSet<String> hashSet = list.stream()
                .map(Employee::getEmpName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    // 收集流中元素的总数
    @Test
    public void testCounting() {
        Long counting = list.stream()
                .map(Employee::getAge)
                .distinct()
                .collect(Collectors.counting());
        System.out.println(counting);
    }

    // 获取流中元素的平均值
    @Test
    public void testAveraging() {
        Double averaging = list.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(averaging);
    }

    // 获取流中元素的总和
    @Test
    public void testSumming() {
        Double sum = list.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
    }

    // 获取流中的最大值
    @Test
    public void testMaxBy() {
        Optional<Double> max = list.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compareTo));
        System.out.println(max.get());
    }

    // 获取流中的最小值
    @Test
    public void testMinBy() {
        Optional<Integer> min = list.stream()
                .map(Employee::getAge)
                .collect(Collectors.minBy(Integer::compareTo));
        System.out.println(min.get());
    }

    // 分组
    @Test
    public void testGroupingBy() {
        Map<Employee.Status, List<Employee>> map = list.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    // 多级分组
    @Test
    public void testGroupingBy1() {
        Map<Employee.Status, Map<Integer, List<Employee>>> map = list.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(Employee::getAge)));
        System.out.println(map);
    }

    // 分区/分片
    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<Employee>> map = list.stream()
                .collect(Collectors.partitioningBy(e -> e.getEmpName().startsWith("Li")));
        System.out.println(map);
    }

    // 收集流中元素的最大值/最小值等封装的对象
    @Test
    public void testSummarizingDouble() {
        DoubleSummaryStatistics summaryStatistics = list.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getCount());
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getSum());
    }

    // 连接字符串
    @Test
    public void testJoining() {
        String join = list.stream()
                .map(employee -> employee.getId().toString())
                .collect(Collectors.joining(";"));
        System.out.println(join);
    }

    // 归约
    @Test
    public void testResuce() {
        Double reduce = list.stream()
                .map(Employee::getSalary)
                .reduce(0D, (x, y) -> x + y);
        System.out.println(reduce);
    }

    @Test
    public void testReduce() {
        Optional<Double> reduce = list.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(reduce.get());
    }

    // 查找与匹配
    // allMatch：检查是否匹配所有元素
    @Test
    public void testAllMatch() {
        boolean allMatch = list.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(allMatch);
    }

    // anyMatch：检查是否至少匹配一个元素
    @Test
    public void testAnyMatch() {
        boolean anyMatch = list.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(anyMatch);
    }

    // noneMatch：检查是否没有匹配所有元素
    @Test
    public void testNoneMatch() {
        boolean noneMatch = list.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(noneMatch);
    }

    // findFirst：返回第一个元素
    @Test
    public void testFindFirst() {
        Optional<Employee> optional = list.stream()
                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                .findFirst();
        System.out.println(optional.get());
    }

    // findAny：返回当前流中的任意元素
    @Test
    public void testFindAny() {
        Optional<Employee> any = list.parallelStream()
                .filter(e -> e.getStatus().equals(Employee.Status.BUSY))
                .findAny();
        System.out.println(any.get());
    }

    // count()：返回流中元素地总数
    @Test
    public void testCount() {
        long count = list.stream()
                .filter(e -> e.getEmpName().startsWith("Li"))
                .count();
        System.out.println(count);
    }

    // max：获取流中的最大值
    @Test
    public void testMax() {
        Optional<Employee> optional = list.stream()
                .filter(e -> e.getEmpName().startsWith("Li"))
                .max((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
        System.out.println(optional.get());
    }

    // min：获取流中的最小值
    @Test
    public void testMin() {
        Optional<Employee> optional = list.stream()
                .filter(e -> e.getEmpName().startsWith("Li"))
                .min((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
        System.out.println(optional.get());
    }

    // 中间操作
    // 排序
    // sorted()：自然排序
    @Test
    public void testSorted(){
        list.stream()
                .map(Employee::getSalary)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    // sorted(Comparator<? super T> comparator)：定制排序
    @Test
    public void testSorted1() {
        list.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge() == e2.getAge()) {
                        return e1.getEmpName().compareTo(e2.getEmpName());
                    } else {
                        return Integer.compare(e1.getAge(), e2.getAge());
                    }
                })
                .distinct()
                .forEach(System.out::println);
    }

    //映射
    // 1. map：将元素转换成其他形式或提取信息
    @Test
    public void testMap() {

        // 将元素转换成其他形式
        List<String> strList = Arrays.asList("lixl", "yucx", "lilx");
        strList.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        // 提取信息
        list.stream()
                .map(Employee::getEmpName)
                .distinct()
                .forEach(System.out::println);
    }

    // 2. flatMap：将流中的每个值都换成另一个流，然后把所有流连接成一个流
    @Test
    public void testFlatMap() {
        list.stream()
                .map(Employee::getEmpName) // 获取集合中员工名字组成得新流
                .map(TestStreamApi::getStream) // 获取每个名字转换成Character流的新流
                .forEach(s -> { // 遍历新流中的每个Character流
                    s.forEach(System.out::println); // 遍历Character流
                    System.out.println("-------------------");
                });
        System.out.println("*****************************");

        list.stream()
                .map(Employee::getEmpName)
                .flatMap(TestStreamApi::getStream)
                .forEach(System.out::println);
    }

    private static Stream<Character> getStream(String str) {
        List<Character> charList = new ArrayList<>();

        for (Character c: str.toCharArray()) {
            charList.add(c);
        }
        return  charList.stream();
    }

    // 筛选与切片
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
