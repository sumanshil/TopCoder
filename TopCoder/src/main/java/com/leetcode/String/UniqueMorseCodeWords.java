package com.leetcode.String;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueMorseCodeWords {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {
                ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."
        };

        Set<String> set = Arrays.stream(words).map(word -> {
            StringBuilder stringBuilder = new StringBuilder(word.length());
            for (int i = 0 ; i < word.length() ; i++) {
                int codeIndex = word.charAt(i) - 'a';
                stringBuilder.append(codes[codeIndex]);
            }
            return stringBuilder.toString();
        }).collect(Collectors.toSet());
        return set.size();
    }

    public static void main(String[] args) {
        String[] arr = {"gin", "zen", "gig", "msg"};
        int result = new UniqueMorseCodeWords().uniqueMorseRepresentations(arr);
        System.out.println(result);
    }
}
