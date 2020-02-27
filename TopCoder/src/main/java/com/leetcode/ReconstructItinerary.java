package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import javafx.collections.transformation.SortedList;

//https://leetcode.com/problems/reconstruct-itinerary/
public class ReconstructItinerary {


    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Queue<String>> map = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        for (List<String> list : tickets) {
            String from = list.get(0);
            String to = list.get(1);
            Queue<String> set = map.getOrDefault(from, new PriorityQueue<>());
            set.add(to);
            map.put(from, set);
            if (!countMap.containsKey(from)) {
                countMap.put(from, 0);
            }
        }
        List<String> result = new LinkedList<>();

        helper(result, map, "JFK");
        return result;
     }

    private void helper(List<String> result, Map<String, Queue<String>> map, String source) {

        Queue<String> queue = map.get(source);


        while(queue != null && !queue.isEmpty()) {
            String nextSource = queue.poll();
            helper(result, map, nextSource);
        }
        result.add(0, source);
    }

    public static void main(String[] args) {
        List<List<String>> lists = new LinkedList<>();
        /**
        List<String> list1 = new LinkedList<>();
        list1.add("MUC");
        list1.add("LHR");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("JFK");
        list1.add("MUC");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("SFO");
        list1.add("SJC");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("LHR");
        list1.add("SFO");
        lists.add(list1);
        **/
        /**
        List<String> list1 = new LinkedList<>();
        list1.add("JFK");
        list1.add("SFO");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("JFK");
        list1.add("ATL");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("SFO");
        list1.add("ATL");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("ATL");
        list1.add("JFK");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("ATL");
        list1.add("SFO");
        lists.add(list1);
         **/

        List<String> list1 = new LinkedList<>();
        list1.add("JFK");
        list1.add("KUL");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("KUL");
        list1.add("JFK");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("JFK");
        list1.add("NRT");
        lists.add(list1);

        list1 = new LinkedList<>();
        list1.add("NRT");
        list1.add("JFK");
        lists.add(list1);

        List<String> result = new ReconstructItinerary().findItinerary(lists);
        System.out.println(result);
    }
}
