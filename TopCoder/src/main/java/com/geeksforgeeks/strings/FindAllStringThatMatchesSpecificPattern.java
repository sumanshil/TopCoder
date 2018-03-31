package com.geeksforgeeks.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sshil on 8/11/2016.
 */
//http://www.geeksforgeeks.org/find-all-strings-that-match-specific-pattern-in-a-dictionary/
public class FindAllStringThatMatchesSpecificPattern {

    private String pattern;
    public void find(String[] arr, String pattern) {
        this.pattern = pattern;

        Arrays.stream(arr)
              .filter(this::check)
              .collect(Collectors.toList());
    }

    private  boolean check(String s) {
        if(s.length() != this.pattern.length()){
            return false;
        }
        Map<String, String > map = new HashMap<>();
        int patternIndex = 0;
        for ( int i = 0 ; i < s.length() ; i++) {
            String s1 = s.charAt(i)+"";
            if (map.containsKey(s1)){
                if (!map.get(s1).equals(this.pattern.charAt(patternIndex)+"")){
                    return false;
                }
            } else {
                String p = this.pattern.charAt(patternIndex)+"";
                map.put(s1, p);
            }
            patternIndex++;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
