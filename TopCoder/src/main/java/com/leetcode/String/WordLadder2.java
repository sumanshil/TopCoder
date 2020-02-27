package com.leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/word-ladder-ii/
public class WordLadder2 {
    int minDistance = Integer.MAX_VALUE;

    /*
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : wordList) {
            if (isValid(beginWord, word)) {
                if (map.containsKey(beginWord)) {
                    map.get(beginWord).add(word);
                } else {
                    List<String> list = new LinkedList<>();
                    list.add(word);
                    map.put(beginWord, list);
                }
            }
        }

        for (String word : wordList) {
            if (word.equals(beginWord)) {
                continue;
            }
            for (String word1 : wordList) {
                if (word.equals(word1) || word1.equals(beginWord)) {
                    continue;
                }

                if (isValid(word, word1)) {

                    if (map.containsKey(word)) {
                        map.get(word).add(word1);
                    } else {
                        List<String> list = new LinkedList<>();
                        list.add(word1);
                        map.put(word, list);
                    }
                }

            }

        }

        List<List<String>> result = new LinkedList<>();
        List<String> current = new LinkedList<>();
        current.add(beginWord);
        recursive(beginWord, map, result, endWord, current);
        return result;
    }
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> graph = new HashMap<>();

        Set<String> dict = new HashSet<>(wordList);

        Set<String> queue = new HashSet<>();

        boolean found = false;

        queue.add(beginWord);

        while (!found && queue.size() > 0) {
            dict.removeAll(queue);
            Set<String> nextLabel = new HashSet<>();
            for (String word : queue) {
                graph.put(word, new ArrayList<>());
                char[] arr = word.toCharArray();
                for (int i = 0 ; i < arr.length ; i++) {
                    char x = arr[i];

                    for (char c = 'a' ; c <= 'z' ; c++) {
                        arr[i] = c;
                        String temp = new String(arr);

                        if (dict.contains(temp)) {
                            nextLabel.add(temp);
                            graph.get(word).add(temp);
                            if (temp.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                    arr[i] = x;
                }
            }
            queue = nextLabel;
        }
        List<List<String>> ans = new LinkedList<>();
        if (!found) {
            return ans;
        }
        List<String> current = new LinkedList<>();
        current.add(beginWord);

        recursive(beginWord, graph, ans, endWord, current);
        return ans;
    }
    private void recursive(String beginWord, Map<String, List<String>> map, List<List<String>> result, String endWord,
                           List<String> current) {

        if (beginWord.equals(endWord)) {
            if (current.size() <= minDistance) {
                result.add(new LinkedList(current));
                minDistance = current.size();
                int index = 0;
                result.removeIf(o -> ((List) o).size() > minDistance);
            }
            return;
        }

        List<String> list = map.get(beginWord);
        if (list != null) {
            for (String word : list) {
                if (current.contains(word)) {
                    continue;
                }
                current.add(word);
                recursive(word, map, result, endWord, current);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isValid(String beginWord, String word) {
        int count = 0;
        for (int i = 0 ; i < beginWord.length() ; i++) {
            if (beginWord.charAt(i) != word.charAt(i)) {
                if (count == 1) {
                    return false;
                } else {
                    count++;
                }
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot");
        List<List<String>> result = new WordLadder2().findLadders("hit", "dog", list);
        System.out.println(result);


    }
}
