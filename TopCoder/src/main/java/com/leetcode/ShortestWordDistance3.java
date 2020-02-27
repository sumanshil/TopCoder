package com.leetcode;

//https://leetcode.com/problems/shortest-word-distance-iii/
public class ShortestWordDistance3 {

    public int shortestWordDistance(String[] words, String word1, String word2) {
        int word1Index = -1;
        int word2Index = -1;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0 ; i < words.length ; i++) {
            if (words[i].equals(word1) && words[i].equals(word2)) {
                if (word1Index != -1) {
                    minLength = Math.min(minLength, i - word1Index);
                }
                word1Index = i;
            } else if (words[i].equals(word1)) {
                if (word1Index != -1 && word2Index != -1) {
                    minLength = Math.min(minLength, (i - word2Index));
                }
                word1Index = i;
            } else if (words[i].equals(word2)) {
                if (word1Index != -1 && word2Index != -1) {
                    minLength = Math.min(minLength, (i - word1Index));
                }
                word2Index = i;
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        int result = new ShortestWordDistance3().shortestWordDistance(words, "makes", "makes");
        System.out.println(result);


    }

}
