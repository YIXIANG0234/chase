package edu.hhuc.yixiang.common.lang;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0
 * @project chase
 * @description
 * @date 2025/6/22 22:15:47
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
