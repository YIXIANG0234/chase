package edu.hhuc.yixiang.common.lang.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/6/20 11:43:53
 */
public class ListDemo {
    public static void main(String[] args) {
        test2();

    }

    public static void test1() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void test2() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(5);
        queue.offer(2);
        queue.offer(4);
        queue.offer(7);
        queue.offer(9);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
