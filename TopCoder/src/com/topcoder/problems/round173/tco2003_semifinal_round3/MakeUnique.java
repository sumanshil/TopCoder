package com.topcoder.problems.round173.tco2003_semifinal_round3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//https://community.topcoder.com/stat?c=problem_statement&pm=1905&rd=4708
public class MakeUnique {

    static Set<String> currentSet = new HashSet<>();
    static String result;
    int maxLength = Integer.MAX_VALUE;
    int uniqueCharacters = 0;
    private static final char EMPTY_CHAR = '.';
    public void find (String str){
        char[] arr = str.toCharArray();
        Set<String> strings = new HashSet<>();
        for (int i = 0 ; i < str.length() ; i++){
            strings.add(str.charAt(i)+"");
        }
        uniqueCharacters = strings.size();
        recursive(0, str, arr);
        System.out.println(result);
        System.out.println(maxLength);
    }

    private void recursive(int currentIndex, String str, char[] arr) {
        if (currentIndex == str.length()) {
            checkForResult(arr);
            return;
        }

        char c = arr[currentIndex];
        if (c != EMPTY_CHAR && isPossibleToPlaceThisCharacter(c, arr, currentIndex)){
            recursive(currentIndex+1,str, arr);
            arr[currentIndex] = EMPTY_CHAR;
            recursive(currentIndex+1, str, arr);
            arr[currentIndex] = c;
        } else if (c != EMPTY_CHAR) {
            arr[currentIndex] = EMPTY_CHAR;
            recursive(currentIndex+1, str, arr);
            arr[currentIndex] = c;
        }
    }

    private void checkForResult(char[] arr) {
        isValid(arr);
    }

    private boolean isValid(char[] arr) {
        int lastValue = Integer.MIN_VALUE;
        int firstIndex = -1;
        int lastIndex = 0;
        int count = 0;
        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == EMPTY_CHAR){
                continue;
            }
            if (firstIndex == -1) {
                firstIndex = i;
            } else {
                lastIndex = i;
            }
            count++;
            if (lastValue == Integer.MIN_VALUE){
                lastValue = arr[i] - 'A';
                continue;
            }

            if (lastValue != Integer.MIN_VALUE && arr[i] - 'A' > lastValue) {
                lastValue = arr[i] - 'A';
            } else {
                return false;
            }
        }
        if (count != uniqueCharacters){
            return false;
        }
        if (lastIndex - firstIndex <= maxLength){
            maxLength = lastIndex - firstIndex;
            StringBuilder newSet = new StringBuilder();
            for (int i = 0 ; i < arr.length ; i++){
                newSet.append(arr[i]+"");
            }
            if (result != null && result.length() > 0) {
                if (result.compareTo(newSet.toString()) > 0){
                    result = newSet.toString();
                }
            } else {
                result = newSet.toString();
            }
        }
        return true;
    }

    private boolean isPossibleToPlaceThisCharacter(char c, char arr[], int index) {
        for (int i = 0 ; i < index ; i++) {
            if (arr[i] == c) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "DDCCADAABACCCDCAABCCACDCCDCCDD";
        new MakeUnique().find(str);
        //System.out.println(".".compareTo("A"));
    }
}
