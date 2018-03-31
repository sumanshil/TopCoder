package com.geeksforgeeks.strings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sshil on 7/31/2016.
 */
//http://www.geeksforgeeks.org/match-a-pattern-and-string-without-using-regular-expressions/
public class FindPatternWithoutRegularExpression {

    private Map<String, Integer> countMap = new HashMap<>();
    private Map<String, Integer> lengthMap = new HashMap<>();
    private Map<String, String> contentMap = new HashMap<>();
    private List<String> list = new LinkedList<>();
    private String pattern = null;
    private String input = null;
    public void find (String input, String pattern) {
        this.input = input;
        this.pattern = pattern;
        String[] arr = new String[pattern.length()];
        for ( int i = 0 ; i < pattern.length() ; i++){
            arr[i] = pattern.charAt(i)+"";
        }
        countMap = Stream.of(arr).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt( e -> 1)));
        list = Stream.of(arr).distinct().collect(Collectors.toList());
        findRecursive(0, input.length());
    }

    private void findRecursive(int currentIndex, int inputLength) {
        if (inputLength < 0){
            return;
        }

        if (currentIndex == list.size()-1) {
            int count = countMap.get(list.get(currentIndex));
            if (inputLength > count) {
                int length = inputLength / count;
                lengthMap.put(list.get(currentIndex), length);
                checkIfPatternExist();
            }
            return;
        }

        String s = list.get(currentIndex);
        for ( int i = 1 ; i < inputLength ; i++){
            lengthMap.put(s, i);
            int count = countMap.get(s);
            int remainingLength = inputLength - (count*i);
            findRecursive(currentIndex+1, remainingLength);
        }
    }

    private void checkIfPatternExist() {
        contentMap.clear();
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (int i = 0 ; i < pattern.length() ; i++) {
            String s = pattern.charAt(i)+"";
            int length = lengthMap.get(s);
            if (!contentMap.containsKey(s)){
                // we can assign
                String str = input.substring(index, index+length);
                stringBuilder.append(str);
                contentMap.put(s, str);
            } else {
                String str = contentMap.get(s);
                stringBuilder.append(str);
            }
            index = index+length;
        }
        if (input.equals(stringBuilder.toString())){
            list.stream().forEach(e ->
                System.out.println(e +" -> "+ contentMap.get(e))
            );
        }
    }

    public static void main(String[] args) {
        String pattern = "GfG";
        String input = "GeeksforGeeks";
        new FindPatternWithoutRegularExpression().find(input, pattern);
    }
}
