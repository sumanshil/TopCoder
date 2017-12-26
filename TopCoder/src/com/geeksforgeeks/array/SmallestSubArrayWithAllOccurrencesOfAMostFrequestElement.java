package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/smallest-subarray-with-all-occurrences-of-a-most-frequent-element/
public class SmallestSubArrayWithAllOccurrencesOfAMostFrequestElement {

    private Map<Integer,Integer> firstIndex = new HashMap<>();
    private Map<Integer,Integer> lastIndex = new HashMap<>();

    int count[];

    public void find (int arr[]) {
        count = new int[100];
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < arr.length ; i++) {
            count[arr[i]]++;
            max = Math.max(count[arr[i]], max);
            if (!firstIndex.containsKey(arr[i])) {
                firstIndex.put(arr[i], i);
            }
            lastIndex.put(arr[i], i);
        }
        int minLength = Integer.MAX_VALUE;
        for ( int i = 0 ; i < count.length ; i++) {
            if (count[i] == max){
                int firstI = firstIndex.get(i);
                int lastI = lastIndex.get(i);
                minLength = Math.min(minLength, (lastI - firstI));
            }
        }
        System.out.println(minLength+1);
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 3, 1};
        new SmallestSubArrayWithAllOccurrencesOfAMostFrequestElement().find(arr);
    }
}
