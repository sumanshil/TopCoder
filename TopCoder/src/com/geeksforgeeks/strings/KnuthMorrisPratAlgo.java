package com.geeksforgeeks.strings;

//http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
public class KnuthMorrisPratAlgo {

    public void find (String text, String pattern) {
        int lps[] = computeLps(pattern);
        int patternIndex = 0;
        int textIndex = 0;
        while (textIndex < text.length()) {
            if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
                patternIndex++;
                textIndex++;
            }
            if (patternIndex == pattern.length()) {
                System.out.println("Pattern found at " + (textIndex - pattern.length()));
                patternIndex = lps[patternIndex-1];
            }
            if (patternIndex < pattern.length() && textIndex < text.length()) {
                if (pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                    if (patternIndex > 0) {
                        patternIndex = lps[patternIndex - 1];
                    } else {
                        textIndex++;
                    }
                }
            }
        }
    }

    private int[] computeLps(String pattern) {
        int lps[] = new int[pattern.length()];
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while ( i < pattern.length()){
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len ++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0){
                    len = lps[len-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String text = "geeksforgeeks";
        String pattern = "geeks";
        new KnuthMorrisPratAlgo().find(text, pattern);
    }

}
