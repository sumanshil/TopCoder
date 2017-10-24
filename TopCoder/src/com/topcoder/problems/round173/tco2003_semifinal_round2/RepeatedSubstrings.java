package com.topcoder.problems.round173.tco2003_semifinal_round2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//https://community.topcoder.com/stat?c=problem_statement&pm=2004&rd=4707
public class RepeatedSubstrings {

    public String decompress(String compressed) {

        List<String> list = new LinkedList<>();

        int index = 0;
        while (index < compressed.length()) {
            if (isChar(compressed.charAt(index)+"") || compressed.charAt(index) == ' ') {
                list.add(compressed.charAt(index)+"");
            } else {
                if (compressed.charAt(index) == '&'){
                    index++;
                }
                int i = index;
                int number1;
                int number2;
                StringBuilder number1Str = new StringBuilder();
                while (i < compressed.length() && compressed.charAt(i) != '-') {
                    number1Str.append(compressed.charAt(i));
                    i++;
                }
                number1 = Integer.parseInt(number1Str.toString());
                i++;
                StringBuilder number2Str = new StringBuilder();
                while (i < compressed.length() && !(isChar(compressed.charAt(i)+"")
                 || compressed.charAt(i) == ' ' || compressed.charAt(i) == '&')) {
                    number2Str.append(compressed.charAt(i));
                    i++;
                }
                number2 = Integer.parseInt(number2Str.toString());
                index = i-1;
                for (int in = number1 ; in <= number2 ; in++) {
                    list.add(in+"");
                }
            }
            index++;
        }

        String[] arr = new String[list.size()];
        for (int i = 0 ; i < list.size() ; i++){
            arr[i] = list.get(i);
        }
        Map<Integer, String> indexToString = new HashMap<>();
        Map<Integer, Integer> indexToPlaceHolderPosition = new HashMap<>();

        for ( int i = 0 ; i < arr.length ; i++) {
            if (isChar(arr[i]) || arr[i].equals(" ")) {
                indexToString.put(i, arr[i]);
            } else {
                indexToPlaceHolderPosition.put(i, Integer.parseInt(arr[i]));
            }
        }

        while (true) {
            boolean found = false;
            Iterator<Map.Entry<Integer, Integer>> iterator = indexToPlaceHolderPosition.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                if (indexToString.containsKey(entry.getValue())){
                    String charac = indexToString.get(entry.getValue());
                    indexToString.put(entry.getKey(), charac);
                    arr[entry.getKey()] = charac;
                    iterator.remove();
                    found = true;
                }
            }
            if (!found) {
                break;
            }
        }

        for (int i = 0 ; i < arr.length ; i++) {
            if (!isChar(arr[i]) && !arr[i].equals(" ")){
                arr[i] = "?";
            }
        }

        String retVal = Arrays.stream(arr).collect(Collectors.joining(""));
        return retVal;
    }

    private boolean isChar(String c) {
        return (c.compareTo("A") >= 0 && c.compareTo("Z") <= 0);
    }

    public static void main(String[] args) {
        String input = "ERCOD&37-41&2-6&27-31&17-21C&14-19&23-27&5-9ERTOP";
        String res = new RepeatedSubstrings().decompress(input);
        System.out.println(res);
    }
}
