package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/word-pattern/
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        int patternIndex = 0;
        Map<String, String> patternToStr = new HashMap<>();
        Map<String, String> stringToPattern = new HashMap<>();

        int index = 0;
        StringBuffer stringBuffer = new StringBuffer();

        while (index < str.length()) {
            if (patternIndex == pattern.length()) {
                return false;
            }
            if (str.charAt(index) == ' ') {
                String strFinal = stringBuffer.toString();
                String patternFinal = pattern.charAt(patternIndex++)+"";
                if (stringToPattern.containsKey(strFinal) && !stringToPattern.get(strFinal).equals(patternFinal)) {
                    return false;
                } else {
                    stringToPattern.put(strFinal, patternFinal);
                }
                if (patternToStr.containsKey(patternFinal) && !patternToStr.get(patternFinal).equals(strFinal)) {
                    return false;
                } else {
                    patternToStr.put(patternFinal, strFinal);
                    stringBuffer = new StringBuffer();
                }
            } else {
                stringBuffer.append(str.charAt(index));
            }
            index++;
        }
        if (patternIndex == pattern.length()) {
            return false;
        }

        String patStr = pattern.charAt(patternIndex++)+"";

        if (stringToPattern.containsKey(stringBuffer.toString()) && !stringToPattern.get(stringBuffer.toString()).equals(patStr)) {
            return false;
        }

        if (patternToStr.containsKey(patStr) && !patternToStr.get(patStr).equals(stringBuffer.toString())) {
            return false;
        }
        return patternIndex == pattern.length();
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog dog dog dog";
        boolean result = new WordPattern().wordPattern(pattern, str);
        System.out.println(result);
    }
}
