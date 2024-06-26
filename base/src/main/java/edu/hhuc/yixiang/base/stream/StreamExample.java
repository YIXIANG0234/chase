package edu.hhuc.yixiang.base.stream;

import edu.hhuc.yixiang.common.constant.StringConstants;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2024/6/20 10:47:20
 */
public class StreamExample {

    public static void main(String[] args) throws Exception {
        test5();
    }

    /**
     * BaseStream中的基本操作
     */
    public static void test1() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);

        // 返回一个迭代器
        Iterator<Integer> iterator = stream.iterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
        }
        System.out.println(sb);

        // 返回分割迭代器
        stream = Stream.of(1, 2, 3, 4, 5);
        Spliterator<Integer> spliterator = stream.spliterator();
        sb.delete(0, sb.length());
        spliterator.forEachRemaining(x -> sb.append(x).append(" "));
        System.out.println(sb);

        stream = Stream.of(1, 2, 3, 4, 5).parallel();
        // 判断是否是并行流
        System.out.println(stream.isParallel());
        // 从一个流中获取串行流
        Stream<Integer> sequential = stream.sequential();
        System.out.println(sequential.isParallel());
    }

    // stream中间操作
    public static void test2() {
        List<String> list = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(list);

        System.out.println(StringConstants.LINE_SEPARATOR);
        list = Stream.of("one", "two", "three", "four")
                .skip(2)
                .limit(1)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    // stream终止操作
    public static void test3() {
        // forEach和forEachOrdered都可以遍历顺序，forEachOrdered保证在顺序流和并行流中都按照顺序输出，forEach在并行流中就不一定按照顺序输出流
        /*
         * 1.forEach和forEachOrdered都可以遍历流
         * 2.forEachOrdered保证在顺序流和并行流中都按照顺序输出
         * 3.forEach在并行流中没法保证按顺序输出
         * 4.forEach和forEachOrdered在顺序流中使用是等价的
         */
        Stream.of("a", "b", "c", "d").forEach(x -> System.out.print(x + " "));
        System.out.println();
        Stream.of("a", "b", "c", "d").forEachOrdered(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println(StringConstants.LINE_SEPARATOR);
        Stream.of("a", "b", "c", "d").parallel().forEach(x -> System.out.print(x + " "));
        System.out.println();
        Stream.of("a", "b", "c", "d").parallel().forEachOrdered(x -> System.out.print(x + " "));
        System.out.println();
    }

    public static void test4() {
        int result = Stream.of(1, 2, 3, 4, 5).reduce(Integer::sum).get();
        System.out.println(result);

        result = Stream.of(1, 2, 3, 4, 5).reduce(-8, Integer::sum);
        System.out.println(result);

        List<Integer> reduceList = Stream.of(1, 2, 3, 4, 5).reduce(new ArrayList<Integer>(), (acc, num) -> {
            System.out.println("acc:" + acc.toString() + ",num:" + num);
            acc.add(num);
            return acc;
        }, (acc, list) -> {
            System.out.println("acc:" + acc.toString() + ",list:" + list.toString());
            acc.addAll(list);
            return acc;
        });
        System.out.println(reduceList);
    }

    public static void test5() {
        IntStream.rangeClosed(1, 10).parallel().forEach(x -> {
            System.out.println(Thread.currentThread().getName() + ":" + x);
        });
        System.out.println(StringConstants.LINE_SEPARATOR);
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        forkJoinPool.submit(() -> {
            int sum = list.stream().reduce(0, Integer::sum);
            System.out.println(Thread.currentThread().getName() + ": sum = " + sum);
        });
    }
}
