package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequentElements {

    class Element {
        int frequency;
        int element;
        Element(int frequency, int element) {
            this.frequency = frequency;
            this.element = element;
        }

    }
    public List<Integer> topKFrequent(int[] nums, int k) {

        Queue<Element> queue = new PriorityQueue<>((ele1, ele2) -> {
            if (ele1.frequency > ele2.frequency) {
                return -1;
            } else {
                return 1;
            }
        });

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            int frequency = map.getOrDefault(num, 0);
            map.put(num, frequency + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.add( new Element(entry.getValue(), entry.getKey()));
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 0 ; i < k ; i++) {
            if (!queue.isEmpty()) {
                result.add(queue.poll().element);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};
        List<Integer> integers = new TopKFrequentElements().topKFrequent(arr, 2);
        System.out.println(integers);
    }
}
