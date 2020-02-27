package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//https://leetcode.com/problems/repeated-dna-sequences/
public class RepeatedDnaSequence {

    public List<String> findRepeatedDnaSequences(String s) {
        /*
        if (s.length() == 0 || s.length() < 10) {
            return  new ArrayList<>();
        }
        Map<String, Integer> map = new HashMap<>();
        String subString = s.substring(0, 10);
        map.put(subString, 1);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(subString);
        for (int i = 10; i < s.length() ; i++) {
            stringBuffer.deleteCharAt(0);
            stringBuffer.append(s.charAt(i));
            if (map.containsKey(stringBuffer.toString())) {
                map.put(stringBuffer.toString(), map.get(stringBuffer.toString()) + 1);
            } else {
                map.put(stringBuffer.toString(), 1);
            }
        }
        return map.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
         */
        Set<Integer> words = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> rv = new ArrayList<>();
        char[] map = new char[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i < s.length() - 9; i++) {
            int v = 0;
            for(int j = i; j < i + 10; j++) {
                v <<= 2;
                v |= map[s.charAt(j) - 'A'];
            }
            if(!words.add(v) && doubleWords.add(v)) {
                rv.add(s.substring(i, i + 10));
            }
        }
        return rv;
    }

    public static void main(String[] args) {
        String str = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> result = new RepeatedDnaSequence().findRepeatedDnaSequences(str);
        System.out.println(result);
    }
}
