package edu.hhuc.yixiang.common.lang.demo;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/6/22 16:47:21
 */
public class SetDemo {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        Random random = new Random();
        int size = 10;
        for (int i = 0; i < size; i++) {
            int num = random.nextInt(100);
            System.out.print(num);
            if (i != size - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
            set.add(num);
        }
        AtomicInteger atomicInteger = new AtomicInteger(0);
        set.forEach(x -> {
            System.out.print(x);
            if (atomicInteger.getAndIncrement() != set.size() - 1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        });
    }
}
