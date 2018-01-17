package com.leetcode.String;

import java.util.Arrays;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubStringWithoutRepeatingCharacters {

    public void find (String str) {
        int indexArr[] = new int[26];
        Arrays.fill(indexArr, -1);
        int startIndex;
        int currentLength = 1;
        int maxLength = 1;
        indexArr[str.charAt(0)-'a'] = 0;
        for ( int i = 1 ; i < str.length() ; i++) {
            char c = str.charAt(i);
            int index = c - 'a';
            if (indexArr[index] == -1) {
                currentLength++;
                indexArr[index] = i;
                maxLength = Math.max(currentLength, maxLength);
            } else {
                int ind = indexArr[index];
                if (ind < i - currentLength) {
                    currentLength++;
                    maxLength = Math.max(currentLength, maxLength);
                } else {
                    startIndex = ind+1;
                    currentLength = i-startIndex+1;
                }
                indexArr[index] = i;
            }
        }
        System.out.println(maxLength);
    }


    public static void main(String[] args) {
        String str = "leetcode";
        new LongestSubStringWithoutRepeatingCharacters().find(str);
    }

}
