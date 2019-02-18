package com.leetcode.integer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

//https://leetcode.com/problems/verifying-an-alien-dictionary/
public class VerifyingAnAlienDictionary {

    interface CheckIfSmaller {
        int test(char c1, char c2);
    }
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for ( int i = 0 ; i < order.length() ; i++) {
            map.put(order.charAt(i), i);
        }

        CheckIfSmaller predicate = (c1, c2) -> {
            if (map.get(c1) < map.get(c2)) {
                return -1;
            } else if (map.get(c1) > map.get(c2)) {
                return 1;
            }
            return 0;
        };

        for ( int i = 0 ; i < words.length - 1 ;  i++) {
            for (int j = i+1 ; j < words.length ; j++) {
                String word1 = words[i];
                String word2 = words[j];

                int length = word1.length() < word2.length() ? word1.length() : word2.length();
                boolean isCorrectOrder = false;
                for ( int k = 0 ; k < length ; k++) {
                    if (word1.charAt(k) == word2.charAt(k)) {
                        continue;
                    }
                    int test = predicate.test(word1.charAt(k), word2.charAt(k));
                    if (test == 0) {
                        continue;
                    } else if (test > 0) {
                        return false;
                    } else {
                        isCorrectOrder = true;
                        break;
                    }

                }
                if (isCorrectOrder){
                    continue;
                }
                if (word1.length() > word2.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"apple","app"};
        String order = "abcdefghijklmnopqrstuvwxyz";
        boolean result = new VerifyingAnAlienDictionary().isAlienSorted(words, order);
        System.out.println(result);
    }
}
