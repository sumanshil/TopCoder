package com.geeksforgeeks.strings;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sshil on 3/27/17.
 */
//http://www.geeksforgeeks.org/count-substrings-with-same-first-and-last-characters/
public class CountSubStringWithFirstAndLastCharacter {

    public void find (String str) {
        AtomicInteger result = new AtomicInteger(0);
        Map<String, List<Integer>> map = new HashMap<>();
        for ( int i = 0 ; i < str.length() ; i++) {
            String current = str.charAt(i)+"";
            if (map.containsKey(current)){
                List<Integer> list = map.get(current);
                list.stream().forEach(e -> result.incrementAndGet());
                list.add(i);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(i);
                map.put(current, list);
            }
        }
        map.entrySet().stream().forEach(e -> result.addAndGet(e.getValue().size()));
        System.out.println(result);
    }

    public static void main(String[] args) {
        String str = "abcab";
        new CountSubStringWithFirstAndLastCharacter().find(str);
    }
}
