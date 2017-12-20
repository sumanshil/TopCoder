package com.geeksforgeeks.array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//http://www.geeksforgeeks.org/count-subarrays-equal-number-1s-0s/
public class CountSubArraysWithEqualNumberOfZerosAndOnes {

    public void find (int arr[]) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == 0) {
                arr[i] = -1;
            }
        }

        int sum = arr[0];
        int i;
        List<Integer> list = new LinkedList<>();
        list.add(0);
        map.put(sum, list);

        for (i = 1 ; i < arr.length ; i++) {
            sum += arr[i];
            if (map.containsKey(sum)) {
                list = map.get(sum);
                list.add(i);
            } else {
                list = new LinkedList<>();
                list.add(i);
                map.put(sum, list);
            }
        }


        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();

            List<Integer> value = entry.getValue();
            if (key != 0) {
                int size = value.size();
                for (int j = 0 ; j < size ; j++) {
                    for ( int k = j + 1; k < size ; k++) {
                        System.out.println("("+(value.get(j)+1)+","+value.get(k)+")");
                    }
                }
            } else {
                int size = value.size();
                for (int j = 0 ; j < size ; j++) {
                    for ( int k = j + 1; k < size ; k++) {
                        System.out.println("("+(value.get(j)+1)+","+value.get(k)+")");
                    }
                }
            }
        }
        List<Integer> list1 = map.get(0);
        for (Integer intr : list1) {
            System.out.println("("+0+","+intr+")");
        }


    }

    public static void main(String[] args) {
        int arr[] = {1, 0, 0, 1, 0, 1, 1};
        new CountSubArraysWithEqualNumberOfZerosAndOnes().find(arr);
    }

}
