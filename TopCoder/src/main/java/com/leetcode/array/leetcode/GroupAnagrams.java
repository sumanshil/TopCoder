package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.topcoder.problems.round13.Array;

//https://leetcode.com/problems/group-anagrams/
public class GroupAnagrams {

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int arr[] = new int[26];

            for ( int i = 0 ; i < str.length() ; i++) {
                arr[str.charAt(i) - 'a']++;
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0 ; i < arr.length ; i++) {
                stringBuffer.append(arr[i]);
            }
            String s1 = stringBuffer.toString();
            if (map.containsKey(s1)) {
                map.get(s1).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s1, list);
            }
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String str1 = new String(arr).intern();
            if (map.containsKey(str1)) {
                map.get(str1).add(str);
            } else {
                List<String> result = new ArrayList<>();
                result.add(str);
                map.put(str1, result);
            }
        }
        return map.values().stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = new GroupAnagrams().groupAnagrams1(input);
        res.stream().forEach(System.out::println);
    }
}
