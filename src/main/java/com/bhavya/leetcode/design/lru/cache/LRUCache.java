package com.guavus.jobs2.bhavya.leetcode.design.lru.cache;

import java.util.HashMap;
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
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


/**
 * Implementation using HashMap and Doubly Linked List
 */
public class LRUCache {
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }

    private Map<Integer, Node> map;
    private Node start;
    private Node end;
    private int cSize;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cSize = 0;
        this.map = new HashMap<>(capacity);
        start = new Node(-1, -1);
        end = new Node(-1, -1);
        start.next = end;
        end.prev = start;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node temp = map.get(key);
            removeNode(temp);
            addNode(temp);
            return temp.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node temp = map.get(key);
            temp.val = value;
            removeNode(temp);
            temp = addNode(temp);
            map.put(key, temp);
        } else {
            if (cSize == capacity) {
                Node last = end.prev;
                map.remove(last.key);
                removeNode(last);
            }
            Node in = new Node(key, value);
            in = addNode(in);
            map.put(key, in);
            if (cSize < capacity) {
                cSize++;
            }
        }
    }

    private Node addNode(Node in) {
        Node temp = start.next;
        start.next = in;
        in.next = temp;
        temp.prev = in;
        in.prev = start;
        return in;
    }

    private void removeNode(Node in) {
        Node next = in.next;
        Node prev = in.prev;
        prev.next = next;
        next.prev = prev;
    }
}
