package com.leetcode.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/word-ladder/
public class WordLadder {
    int minSeq = Integer.MAX_VALUE;
    class BfsNode {
        String word;
        int distance;

        public BfsNode(String beginWord, int i) {
            this.word = beginWord;
            this.distance = i;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new LinkedList<>();
        for (String word : wordList) {
            if (word.equals(beginWord)) {
                continue;
            }
            if (isValid(word, beginWord)) {
                list.add(word);
            }
        }
        map.put(beginWord, list);

        for (String word : wordList) {

            if (word.equals(beginWord)) {
                continue;
            }
            list = new LinkedList<>();
            for (String word1 : wordList) {
                if (word1.equals(word) || word1.equals(beginWord)) {
                    continue;
                }
                if (isValid(word, word1)) {
                    list.add(word1);
                }
            }
            map.put(word, list);
        }

        Queue<BfsNode> queue = new LinkedList<>();
        queue.add(new BfsNode(beginWord, 1));
        Set<String> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            BfsNode bfsNode = queue.poll();
            List<String> words = map.get(bfsNode.word);
            visited.add(bfsNode.word);
            if (bfsNode.word.equals(endWord)) {
                minSeq = Math.min(minSeq, bfsNode.distance);
                break;
            }

            for (String word : words) {
                if (visited.contains(word)) {
                    continue;
                } else {
                    queue.add(new BfsNode(word, bfsNode.distance + 1));
                }
            }
        }
        return minSeq == Integer.MAX_VALUE ? 0 : minSeq;
    }

    /**
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        List<String> currentList = new LinkedList<>();
        currentList.add(beginWord);
        recursive(beginWord, endWord, wordList, currentList, beginWord);
        return minSeq == Integer.MAX_VALUE ? 0 : minSeq;
    }

    private void recursive(String beginWord, String endWord, List<String> wordList, List<String> current,
                           String currentWord) {
        if (current.size() == wordList.size()+1) {
            return;
        }
        if (currentWord.equals(endWord)) {
            minSeq = Math.min(minSeq, current.size());
            return;
        }

        for (String word : wordList) {
            if (current.contains(word)) {
                continue;
            }
            if (isValid(word, currentWord)) {
                current.add(word);
                recursive(beginWord, endWord, wordList,  current, word);
                current.remove(current.size()-1);
            }
        }
    }
    **/
    private boolean isValid(String word, String currentWord) {
        int count = 0;
        for (int i = 0 ; i < word.length() ; i++) {
            if (word.charAt(i) != currentWord.charAt(i)) {
                if (count == 1) {
                    return false;
                }
                count++;
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(new WordLadder().ladderLength(beginWord, endWord, wordList));

    }
}
