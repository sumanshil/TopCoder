package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sshil on 8/29/16.
 */
//http://www.geeksforgeeks.org/find-distinct-subsets-given-set/
public class FIndAllDistinctSubSetsOfAGivenSet {

    private List<List<Integer>> list =  new ArrayList<>();

    public void find(int[] arr) {
        recursive(arr, 0, 0);
        list.stream().forEach(e -> {
            String str = e.stream().map(i -> i+"").collect(Collectors.joining(","));
            System.out.println(str);
            System.out.println();
        });
    }

    private void recursive(int[] arr, int index, int length) {
        if (index >= arr.length) {
            return;
        }

        for (int j = index ; j < arr.length ; j++) {
            if (index == 0) {
                length++;
            }
            List<Integer> newList = new ArrayList<>();
            for (int i = index; i < (index + length) && ((index+length) <= arr.length); i++) {
                newList.add(arr[i]);
            }
            if (newList.size() > 0) {
                list.add(newList);
            }
            recursive(arr, (index+1), length);
            if (index != 0){
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2};
        new FIndAllDistinctSubSetsOfAGivenSet().find(arr);
    }
}
