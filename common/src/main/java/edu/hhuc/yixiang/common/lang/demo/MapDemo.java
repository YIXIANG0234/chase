package edu.hhuc.yixiang.common.lang.demo;

import edu.hhuc.yixiang.common.lang.LRUCache;

import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/6/22 17:33:45
 */
public class MapDemo {
    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
                Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
//        Map<String, String> map = new LinkedHashMap<>();
        map.put("key2", "value2");
        map.put("key5", "value5");
        map.put("key1", "value1");
        map.put("key4", "value4");
        map.put("key3", "value3");

        System.out.println(map.keySet());
        map.get("key2");
        System.out.println(map.keySet());
    }

    public static void test2() {
        LRUCache<String, String> map = new LRUCache<>(3);
        map.put("key2", "value2");
        map.put("key5", "value5");
        map.put("key1", "value1");
        System.out.println(map.keySet());

        map.get("key2");
        map.put("key4", "value4");
        System.out.println(map.keySet());
    }

    public static void test3() {
        Hashtable<String, String> hashtable = new Hashtable<>();
//        hashtable.put("name", null);
        hashtable.put(null, "test");
    }
}

