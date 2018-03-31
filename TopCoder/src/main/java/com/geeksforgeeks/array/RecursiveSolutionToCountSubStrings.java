package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//http://www.geeksforgeeks.org/recursive-solution-count-substrings-first-last-characters/
public class RecursiveSolutionToCountSubStrings {

    public void findIterative (String str){
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0 ; i < str.length() ; i++) {
            String key = str.charAt(i) + "";
            if (map.containsKey(key)) {
                map.get(key).add(i);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(i);
                map.put(key, list);
            }
        }

        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> indexes = entry.getValue();
            for ( int i = 0 ; i < indexes.size() ; i++){
                for ( int j = i + 1; j < indexes.size() ; j++) {
                    int i1 = indexes.get(i);
                    int i2 = indexes.get(j);
                    String sub = str.substring(i1, i2+1);
                    System.out.println(sub);
                }
            }
        }
    }

    public void find (String str) {
        int result = recursive(str, 0, str.length()-1, str.length());
        System.out.println(result);
    }

    private int recursive(String str, int index1, int index2, int length) {
        if (length == 1) {
            return 1;
        }

        if (length <= 0) {
            return 0;
        }

        int res = recursive(str, index1+1, index2, length-1)
                + recursive(str, index1, index2-1, length-1)
                - recursive(str, index1 + 1, index2-1, length-2);

        if (str.charAt(index1) == str.charAt(index2)) {
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "abcab";
        new RecursiveSolutionToCountSubStrings().find(str);
    }
}
