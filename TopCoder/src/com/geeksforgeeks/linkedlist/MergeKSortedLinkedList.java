package com.geeksforgeeks.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 7/6/17.
 */
public class MergeKSortedLinkedList {

    public void find(List<List<Integer>> arr){
        int[] indexArr = new int[arr.size()];
        List<Integer> result = new ArrayList<>();

        while (true) {
            int minElement = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0 ; i < indexArr.length ; i++) {
                int index = indexArr[i];
                if (index < arr.get(i).size() && arr.get(i).get(index) < minElement){
                    minElement = arr.get(i).get(index);
                    minIndex = i;
                }
            }
            if (minElement == Integer.MAX_VALUE){
                break;
            }
            indexArr[minIndex] = indexArr[minIndex]+1;
            result.add(minElement);
        }
        result.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(8);

        List<Integer> list3 = new ArrayList<>();
        list3.add(0);
        list3.add(9);
        list3.add(10);
        list3.add(11);
        List<List<Integer>> arr = new ArrayList<>();
        arr.add(0, list1);
        arr.add(1, list2);
        arr.add(2, list3);
        new MergeKSortedLinkedList().find(arr);
    }
}
