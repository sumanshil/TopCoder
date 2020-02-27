package com.leetcode;

//https://leetcode.com/problems/shortest-word-distance/
public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {
        int word1Index = -1;
        int word2Index = -1;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0 ; i < words.length ; i++) {
            if (words[i].equals(word1)) {
                word1Index = i;
                if (word2Index != -1) {
                    minLength = Math.min(minLength, (i - word2Index));
                }
            } else if (words[i].equals(word2)) {
                word2Index = i;
                if (word1Index != -1) {
                    minLength = Math.min(minLength, (i - word1Index));
                }
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        int res = new ShortestWordDistance().shortestDistance(words, "makes", "coding");
        System.out.println(res);
    }
}
