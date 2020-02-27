package com.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/bulls-and-cows/
public class BullsAndCows {

    public String getHint(String secret, String guess) {
        Map<String, Integer> secretMap = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        for (int i = 0 ; i < secret.length() ; i++) {
            String s1 = secret.charAt(i) + "";
            int count = secretMap.getOrDefault(s1, 0);
            secretMap.put(s1, count + 1);
        }

        for (int i = 0 ; i < guess.length() ; i++) {
            String s1 = secret.charAt(i) + "";
            String s2 = guess.charAt(i) + "";

            if (s1.equals(s2)) {
                int count = secretMap.get(s1);
                secretMap.put(s1, count - 1);
                bulls++;
            }
        }

        for (int i = 0 ; i < guess.length() ; i++) {
            String s1 = secret.charAt(i) + "";
            String s2 = guess.charAt(i) + "";

            if (!s1.equals(s2) && secretMap.containsKey(s2)) {
                int count = secretMap.get(s2);
                if (count > 0) {
                    secretMap.put(s2, count - 1);
                    cows++;
                }
            }
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";
        String result = new BullsAndCows().getHint(secret, guess);
        System.out.println(result);
    }
}
