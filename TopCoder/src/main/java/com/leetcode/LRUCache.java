package com.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/lru-cache/
public class LRUCache {
    class LRUNode {
        int key;
        int value;
        LRUNode next;
        LRUNode prev;

        public LRUNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private int maxCapacity;
    private int currentCapacity;
    private Map<Integer, LRUNode> map = new HashMap<>();
    private LRUNode head = new LRUNode(-1, -1);
    private LRUNode tail = head;

    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.currentCapacity = 0;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int temp = map.get(key).value;
            LRUNode tempNode = map.get(key);
            moveToLastPosition(tempNode);
            return temp;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (currentCapacity == maxCapacity) {
            if (!map.containsKey(key)) {
                LRUNode tempNode = head.next;
                int oldValue = tempNode.key;
                moveToLastPosition(tempNode);
                tempNode.value = value;
                tempNode.key = key;
                map.remove(oldValue);
            } else {
                // already have the element, just update it
                LRUNode tempNode = map.get(key);
                moveToLastPosition(tempNode);
                tempNode.value = value;
            }
        } else {
            // we are under capacity. just append it.
            if (!map.containsKey(key)) {
                LRUNode newNode = new LRUNode(key, value);
                moveToLastPosition(newNode);
                currentCapacity++;
            } else {
                LRUNode tempNode = map.get(key);
                moveToLastPosition(tempNode);
                tempNode.value = value;
            }

        }
        map.put(key, tail);
    }

    private void moveToLastPosition(LRUNode node) {
        if (node == this.tail) {
            return;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        node.next = null;
        node.prev = null;

        tail.next = node;
        node.prev = tail;
        tail = tail.next;
    }


    public static void main(String[] args) {
        /*
        LRUCache cache = new LRUCache( 1);
        System.out.println(cache.get(1));
        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
         */
        LRUCache cache = new LRUCache( 10);
        cache.put(10, 13);
        cache.put(3, 17);
        cache.put(6, 11);
        cache.put(10, 5);
        cache.put(9, 10);
        System.out.println(cache.get(13));
        cache.put(2, 19);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(5, 25);
        System.out.println(cache.get(8));
        cache.put(9, 22);
        cache.put(5, 5);
        cache.put(1, 30);
        System.out.println(cache.get(11));
        cache.put(9, 12);
        System.out.println(cache.get(7));
        System.out.println(cache.get(5));
        System.out.println(cache.get(8));
        System.out.println(cache.get(9));
        cache.put(4, 30);
        cache.put(9, 3);
        System.out.println(cache.get(9));
        System.out.println(cache.get(10));
        System.out.println(cache.get(10));
        cache.put(6, 14);
        cache.put(3, 1);
        System.out.println(cache.get(3));
        cache.put(10, 11);
        System.out.println(cache.get(8));
        cache.put(2, 14);
        System.out.println(cache.get(1));
        System.out.println(cache.get(5));
        System.out.println(cache.get(4));
        cache.put(11, 4);
        cache.put(12, 24);
        cache.put(5, 18);


    }
}
