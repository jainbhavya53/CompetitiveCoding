package com.bhavya.leetcode.design.lru.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author bhavya.jain
 * <p>
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * Implementation using Java's LinkedHashMap
 */

public class LRUCacheLinkedHashMapImpl {
    Map<Integer, Integer> map;

    public LRUCacheLinkedHashMapImpl(int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75F, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
