package com.leetcode.String;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingCharacters1 {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<String,Integer> map = new HashMap<>();

        int maxLength = Integer.MIN_VALUE;

        int currentLength = 0;
        int currentStartIndex = 0;
        for ( int i = 0 ; i < s.length() ; i++) {
            String s1 = s.charAt(i) + "";
            if (!map.containsKey(s1)) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                int lastSeen = map.get(s1);
                if (lastSeen >= currentStartIndex) {
                    currentStartIndex = lastSeen+1;
                    currentLength = (i - currentStartIndex)+1;
                } else {
                    currentLength++;
                }
            }
            map.put(s1, i);
        }
        if (currentLength > 1) {
            maxLength = Math.max(maxLength, currentLength);
        }
        return maxLength == Integer.MIN_VALUE ? 1 : maxLength;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        int res = new LongestSubstringWithoutRepeatingCharacters1().lengthOfLongestSubstring(s1);
        System.out.println(res);
    }
}
