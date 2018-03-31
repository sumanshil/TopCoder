package com.leetcode.array.leetcode;

//https://leetcode.com/problems/median-of-two-sorted-arrays/
public class MedianOfTwoSortedArrays {

    public void find (int x[], int y[]) {
        int start;
        int end;
        int startOther;
        int endOther;
        int smaller[];
        int larger[];
        if (x.length < y.length) {
            start = 0;
            end = x.length - 1;
            endOther = y.length-1;
            smaller = x;
            larger = y;
        } else {
            start = 0;
            end = y.length - 1;
            endOther = x.length -1;
            larger = x;
            smaller = y;
        }

        while(start < end) {
            int partitionX = (start + end)/2;
            int partitionY = (x.length + y.length + 1) - partitionX;

        }
    }

    public static void main(String[] args) {

    }

}
