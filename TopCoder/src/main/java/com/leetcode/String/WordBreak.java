package com.leetcode.String;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//https://leetcode.com/problems/word-break/
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        //Map<Integer, List<String>> map = wordDict.stream().collect(Collectors.groupingBy(String::length));
        //System.out.println(map);
        //boolean result = recursive(0, s, map);
        boolean result = dp(s, wordDict);
        return result;
    }

    private boolean recursive(int index, String input, Map<Integer, List<String>> wordDict) {
        if (index >= input.length()) {
            return true;
        }

        for (Map.Entry<Integer, List<String>> entry : wordDict.entrySet()) {
            String str = input.substring(index, index + entry.getKey());
            if (entry.getValue().contains(str) && recursive(index + entry.getKey(), input, wordDict)) {
                return true;
            }
        }
        return false;
    }

    private boolean dp(String s, List<String> wordDict) {
        boolean dp[] = new boolean[s.length()+1];
        dp[0] = true;
        Map<Integer, List<String>> map = wordDict.stream().collect(Collectors.groupingBy(String::length));
        for (int i = 1 ; i <= s.length() ; i++) {
            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                if (entry.getKey() <= (i)) {
                    int length2 = (i) - entry.getKey();
                    String lastSuffix = s.substring(length2, i);
                    if (dp[length2] && map.containsKey(lastSuffix.length()) && map.get(lastSuffix.length()).contains(lastSuffix)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }


    public static void main(String[] args) {
        String input = "catsandog";
        List<String> list = Arrays.asList("cats", "dog", "sand", "and", "cat");
        //String input = "applepenapple";
        //List<String> list = Arrays.asList("apple", "pen");
        boolean result = new WordBreak().wordBreak(input, list);
        System.out.println(result);
    }
}
