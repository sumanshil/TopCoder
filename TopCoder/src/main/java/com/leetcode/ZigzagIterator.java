package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/zigzag-iterator/
public class ZigzagIterator {
    int vectorIndex = -1;
    int elementIndex = 0;
    List<List<Integer>> listOfList = new LinkedList<>();
    List<Integer> listOfIndexes = new LinkedList<>();
//    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
//        this.listOfList.add(v1);
//        this.listOfList.add(v2);
//
//        this.listOfIndexes.add(0);
//        this.listOfIndexes.add(0);
//
//        //this.hasNext();
//    }

    public ZigzagIterator(List<List<Integer>> v1) {
        for (int i = 0 ; i < v1.size() ; i++) {
            this.listOfList.add(v1.get(i));
            this.listOfIndexes.add(0);
        }
    }

    public int next() {
        int temp = this.listOfList.get(this.vectorIndex).get(this.elementIndex);
        return temp;
    }

    public boolean hasNext() {
        int currentVectorIndex = this.vectorIndex;
        for (int i = currentVectorIndex + 1 ; i < this.listOfList.size() ; i++) {
            if (this.listOfIndexes.get(i) < this.listOfList.get(i).size()) {
                this.vectorIndex = i;
                this.elementIndex = this.listOfIndexes.get(i);
                this.listOfIndexes.set(i, this.elementIndex + 1);
                return true;
            }
        }

        for (int i = 0 ; i <= currentVectorIndex ; i++) {
            if (this.listOfIndexes.get(i) < this.listOfList.get(i).size()) {
                this.vectorIndex = i;
                this.elementIndex = this.listOfIndexes.get(i);
                this.listOfIndexes.set(i, this.elementIndex + 1);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7);
        List<Integer> list3 = Arrays.asList(8, 9);

        //        List<Integer> list2 = new LinkedList<>();
//        List<Integer> list1 = Arrays.asList(3, 4, 5, 6);

        ZigzagIterator iterator = new ZigzagIterator(Arrays.asList(list1, list2, list3));
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
