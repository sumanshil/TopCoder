package com.leetcode.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyboardRow {

    static Map<String, Integer> keyMap = new HashMap<>();
    static {
        keyMap.put("Q", 0);
        keyMap.put("W", 0);
        keyMap.put("E", 0);
        keyMap.put("R", 0);
        keyMap.put("T", 0);
        keyMap.put("Y", 0);
        keyMap.put("U", 0);
        keyMap.put("I", 0);
        keyMap.put("O", 0);
        keyMap.put("P", 0);

        keyMap.put("A", 1);
        keyMap.put("S", 1);
        keyMap.put("D", 1);
        keyMap.put("F", 1);
        keyMap.put("G", 1);
        keyMap.put("H", 1);
        keyMap.put("J", 1);
        keyMap.put("K", 1);
        keyMap.put("L", 1);

        keyMap.put("Z", 2);
        keyMap.put("X", 2);
        keyMap.put("C", 2);
        keyMap.put("V", 2);
        keyMap.put("B", 2);
        keyMap.put("N", 2);
        keyMap.put("M", 2);
    }


    public String[] findWords(String[] words) {

        List<String> resList = Arrays.asList(words).stream().filter(KeyboardRow::checkIfInSameRow)
                .collect(Collectors.toList());

        return resList.toArray(new String[0]);
    }

    private static boolean checkIfInSameRow(String s) {
        int currentRow = -1;
        for (int i = 0 ; i < s.length() ; i++) {
            if ( currentRow == -1) {
                currentRow = keyMap.get((s.charAt(i) +"").toUpperCase());
            } else if (currentRow != keyMap.get((s.charAt(i) +"").toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"Hello", "Alaska", "Dad", "Peace"};
        String[] result = new KeyboardRow().findWords(words);
        Arrays.asList(result).stream().forEach(System.out::println);
    }
}
