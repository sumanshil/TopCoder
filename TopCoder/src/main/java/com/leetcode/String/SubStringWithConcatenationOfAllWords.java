package com.leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

//https://leetcode.com/problems/substring-with-concatenation-of-all-words/
public class SubStringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0 || words.length == 0) {
            return new ArrayList<>();
        }
        int totalWordLength = words.length * words[0].length();

        if (totalWordLength > s.length()) {
            return new ArrayList<>();
        }

        Map<String, Long> lookUpSet = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(),
                                                                                            Collectors.counting()));
        List<Integer> retVal = new LinkedList<>();

        int wordLength = words[0].length();

        for ( long i = 0 ; i < wordLength ; i++) {
            Map<String, Long> currMap = new HashMap<>();
            long j = 0;
            for (j = i ; j  < i + totalWordLength && j + wordLength <= s.length() ; j = j + wordLength) {
                String subString = s.substring((int)j , (int)(j + wordLength));

                if (lookUpSet.containsKey(subString)) {
                    currMap.put(subString, currMap.getOrDefault(subString, 0L) + 1);
                }
            }
            if (currMap.equals(lookUpSet)) {
                retVal.add(new Long(i).intValue());
            }
            long left = i;

            while (j + wordLength <= s.length()) {
                String leftStr = s.substring((int)left, (int)(left + wordLength));
                String rightStr = s.substring((int)j, (int)(j + wordLength));

                if (lookUpSet.containsKey(leftStr)) {
                    if (currMap.get(leftStr) > 1) {
                        currMap.put(leftStr, currMap.get(leftStr)-1);
                    } else {
                        currMap.remove(leftStr);
                    }
                }
                if (lookUpSet.containsKey(rightStr)) {
                    currMap.put(rightStr, currMap.getOrDefault(rightStr, 0L) + 1);
                }
                left = left + wordLength;
                j = j + wordLength;
                if (currMap.equals(lookUpSet)) {
                    retVal.add(new Long(left).intValue());
                }

            }

        }
        return retVal;
    }

    private boolean solutionExists(int i, String s, String[] words, Map<String, Long> lookUpSet) {
        Map<String, Long> currentMap = new HashMap<>();
        int wordCount = words[0].length();
        int totalWordCount = words.length * wordCount;
        StringBuffer stringBuffer = new StringBuffer(wordCount);
        int indexCount = 0;
        for (int j = i ; j < i + totalWordCount && j < s.length(); j++) {
            stringBuffer.append(s.charAt(j));

            indexCount++;
            if (indexCount == wordCount) {

                String key = stringBuffer.toString();
                if (!lookUpSet.containsKey(key)) {
                    return false;
                }
                currentMap.put(key, currentMap.getOrDefault(key, 0L) + 1);
                if (lookUpSet.get(key) < currentMap.get(key)) {
                    return false;
                }

                indexCount = 0;
                stringBuffer = new StringBuffer(wordCount);
            }
        }
        return currentMap.equals(lookUpSet);
    }

    public static void main(String[] args) {
        //String s = "barfoofoobarthefoobarman";
        //String[] words = {"bar","foo", "the"};
        String s ="wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        List<Integer> result = new SubStringWithConcatenationOfAllWords().findSubstring(s, words);
        for ( int i : result) {
            System.out.println(i);
        }
    }
}
