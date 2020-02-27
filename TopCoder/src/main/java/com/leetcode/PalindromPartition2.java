package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PalindromPartition2 {

    public List<String> generatePalindromes(String s) {
        //int[] count = new int[26];
        Map<String, Integer> count = new HashMap<>();
        List<String> set = new LinkedList<>();
        for (int i = 0 ; i < s.length() ; i++) {
            int index = s.charAt(i) - 'a';
            //count[index] = count[index] + 1;
            int c = count.getOrDefault(s.charAt(i)+"", 0);
            if (!set.contains(s.charAt(i)+"")) {
                set.add(s.charAt(i) + "");
            }
            count.put(s.charAt(i)+"", c + 1);
        }

        int oddCount = 0;
        String oddChar = "";
        for (int i = 0 ; i < set.size() ; i++) {
            if (count.get(set.get(i)) % 2 != 0) {
                oddCount++;
                oddChar = (set.get(i));
            }
        }

        if (s.length() % 2 == 0) {
            if (oddCount > 0) {
                return new LinkedList<>();
            }
        } else {
            if (oddCount != 1) {
                return new LinkedList<>();
            }
        }

        List<String> current = new LinkedList<>();
        List<String> result = new LinkedList<>();
        Map<String, Integer> currentCount = new HashMap<>();
        if (s.length() % 2 != 0) {
            current.add(oddChar);
            int index = oddChar.charAt(0) - 'a';
            int c = count.get(oddChar);
            count.put(oddChar, c -1);
            recursive(0, set, current, s, count, currentCount, result);
        } else {
            recursive(0, set, current, s, count, currentCount, result);
        }
        return result;
    }

    private void recursive(int index, List<String> set, List<String> current, String originalString,
                           Map<String, Integer> count, Map<String, Integer> currentCount, List<String> result) {
        if (current.size() == originalString.length()) {
            String res = current.stream().collect(Collectors.joining());
            if (!result.contains(res)) {
                result.add(res);
            }
            return;
        }

        for(int i = 0 ; i < set.size(); i++ ) {
            String s = set.get(i);
            int idx = s.charAt(0) - 'a';
            if (currentCount.getOrDefault(s, 0) < count.get(s)) {
                //currentCount[idx] = currentCount[idx] + 2;
                int c = currentCount.getOrDefault(s, 0);
                currentCount.put(s, c + 2);
                current.add(0, s);
                current.add(s);
                recursive(i, set, current, originalString, count, currentCount, result);
                c = currentCount.get(s);
                currentCount.put(s, c - 2);
                current.remove(0);
                current.remove(current.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        String str ="civic";
        List<String> result = new PalindromPartition2().generatePalindromes(str);
        System.out.println(result);
    }
}
