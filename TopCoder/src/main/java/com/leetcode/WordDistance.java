package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/shortest-word-distance-ii/
public class WordDistance {
    Map<String, List<Integer>> map = new HashMap<>();

    public WordDistance(String[] words) {
        for (int i = 0 ; i < words.length ; i++) {
            String word = words[i];
            if (map.containsKey(word)) {
                map.get(word).add(i);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(i);
                map.put(word, list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> postion1 = map.get(word1);
        List<Integer> position2 = map.get(word2);
        int minDistance = Integer.MAX_VALUE;
        for (int i : postion1) {
            for (int j : position2) {
                minDistance = Math.min(minDistance, Math.abs(j-i));
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        String[] arr = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance wordDistance = new WordDistance(arr);
        int res = wordDistance.shortest("coding", "practice");
        System.out.println(res);
        res = wordDistance.shortest("makes", "coding");
        System.out.println(res);

    }
}
