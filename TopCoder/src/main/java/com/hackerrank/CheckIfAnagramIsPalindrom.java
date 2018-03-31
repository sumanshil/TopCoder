package com.hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 4/13/2016.
 */
//https://www.hackerrank.com/challenges/game-of-thrones?h_r=next-challenge&h_v=legacy
public class CheckIfAnagramIsPalindrom {
    public static void main(String[] args) {
        String str = "cdefghmnopqrstuvw";
        Map<String, Integer> characterCountMap = new HashMap<>();
        for ( int i = 0 ; i < str.length() ; i++) {
            char c = str.charAt(i);
            String key = c+"";
            if (characterCountMap.containsKey(key)){
                characterCountMap.put(key, characterCountMap.get(key)+1);
            } else {
                characterCountMap.put(key, 1);
            }
        }
        long count = characterCountMap
            .entrySet()
            .stream()
            .filter(e -> e.getValue()%2 != 0)
            .count();
        System.out.println(count);
    }
}
