package com.geeksforgeeks.backtracking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//http://www.geeksforgeeks.org/combinations-every-element-appears-twice-distance-appearances-equal-value/
public class CombinationsEveryElementAppearTwice {

    static Map<Integer, List<Integer>> lastPositionMap = new HashMap<>();
    static Map<Integer, Integer> numberAppearanceFrequency = new HashMap<>();

    public void find(int n) {
        int arr[] = new int[2*n];
        Arrays.fill(arr, -1);
        recursive(arr, 0, n);
    }

    private void recursive(int[] arr, int index, int n) {
        if (index >= arr.length) {
            Arrays.stream(arr).forEach(System.out::print);
            System.out.println();
            return;
        }

        for ( int i = 1 ; i <= n ; i++){
            if (arr[index] == -1) {
                if (isValidNumberForThisPosition(i, index, arr, n)) {
                    arr[index] = i;
                    recursive(arr, index+1, n);
                    int thisNumber = arr[index];
                    backTrack(thisNumber);
                    arr[index] = -1;
                }
            }
        }
    }

    private void backTrack(int thisNumber) {
        List<Integer> positionList = lastPositionMap.get(thisNumber);
        positionList.remove(positionList.size()-1);

        int freq = numberAppearanceFrequency.get(thisNumber);
        numberAppearanceFrequency.put(thisNumber, freq-1);
    }

    private boolean isValidNumberForThisPosition(int number, int index, int[] arr, int n) {
        if (lastPositionMap.containsKey(number)){
            List<Integer> lastPosList = lastPositionMap.get(number);
            if (lastPosList.size() > 0) {
                int lastPos = lastPosList.get(lastPosList.size() - 1);
                if (index - lastPos != (number+1)) {
                    return false;
                }
            }

        }

        if (numberAppearanceFrequency.containsKey(number)) {
            int freq = numberAppearanceFrequency.get(number);
            if (freq >= 2){
                return false;
            }
        }

        if (lastPositionMap.containsKey(number)){
            List<Integer> list = lastPositionMap.get(number);
            list.add(index);
        } else {
            List<Integer> list = new LinkedList<>();
            list.add(index);
            lastPositionMap.put(number, list);
        }

        if (numberAppearanceFrequency.containsKey(number)){
            int freq = numberAppearanceFrequency.get(number);
            numberAppearanceFrequency.put(number, freq + 1);
        } else {
            numberAppearanceFrequency.put(number, 1);
        }
        return true;
    }


    public static void main(String[] args) {
        int n = 3;
        new CombinationsEveryElementAppearTwice().find(n);
    }

}
