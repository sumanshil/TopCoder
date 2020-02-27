package com.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/isomorphic-strings/
public class IsomorphicString {

    public boolean isIsomorphic(String s, String t) {
        /*
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();

        for (int i = 0 ; i < s.length() ; i++) {
            String s1 = s.charAt(i)+"";
            String s2 = t.charAt(i)+"";

            if (map1.containsKey(s1) || map2.containsKey(s2)) {
                if (!(s2).equals(map1.get(s1)) || !(s1).equals(map2.get(s2))) {
                    return false;
                }
            } else if (!map1.containsKey(s1) && map2.containsKey(s2)){
                return false;
            } else if (!map1.containsKey(s1) && !map2.containsKey(s2)) {
                map1.put(s1, s2);
                map2.put(s2, s1);
            }
        }
         */
        return true;
    }

    public static void main(String[] args) {
        String s = "ab";
        String t = "aa";

        boolean res = new IsomorphicString().isIsomorphic(s, t);
        System.out.println(res);
    }
}
