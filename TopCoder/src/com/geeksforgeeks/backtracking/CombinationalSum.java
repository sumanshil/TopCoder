package com.geeksforgeeks.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//http://www.geeksforgeeks.org/combinational-sum/
public class CombinationalSum {

    public void find (int[] arr, int sum) {
        List<Integer> list = new ArrayList<>();
        recursive(arr, sum, sum, list, 0);
    }

    private void recursive(int[] arr, int sum, int remainder, List<Integer> list, int index) {
        int newSum = list.stream().mapToInt(e -> e).sum();
        if (newSum == sum) {
            list.stream().forEach(System.out::print);
            System.out.println();
            return;
        }
        for (int i = index ; i < arr.length; i++) {
            if (arr[i] <= remainder) {
                list.add(arr[i]);
                recursive(arr, sum, remainder-arr[i], list, index);
                list.remove(list.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int sum = 8;
        int[] arr = {2, 4, 6, 8};
        new CombinationalSum().find(arr, sum);
    }

}
