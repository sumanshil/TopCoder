package com.geeksforgeeks.dynamicprogramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 7/12/2016.
 */
//http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
public class MinNumberOfJumpsToReachEnd2 {


    private List<Integer> list = new LinkedList<>();

    public void minJumps(int[] arr){
       int result = minJumpRecursive(arr, 0);
        System.out.println(result);
    }


    private int minJumpRecursive(int[] arr, int currentIndex) {
        if (currentIndex >= arr.length-1){
            return 1;
        }

        if (arr[currentIndex] == 0){
            return Integer.MAX_VALUE;
        }

        int minJumps = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = currentIndex+1 ; i <= currentIndex+arr[currentIndex] ; i++) {
            int jumps  = minJumpRecursive(arr, i);
            if (jumps != Integer.MAX_VALUE && jumps+1 < minJumps){
                minJumps = jumps+1;
                minIndex = i;
            }
        }
        if (!list.contains(minIndex)) {
            list.add(minIndex);
        }
        return minJumps;
    }

    public void findDynamic(int[] arr){
        int[] minJumps = new int[arr.length];
        Arrays.fill(minJumps, Integer.MAX_VALUE);
        minJumps[0] = 1;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1 ; j <= i + arr[i] && j < arr.length ; j++) {
                minJumps[j] = Math.min(minJumps[j], minJumps[i]+1);
            }
        }
        System.out.println(minJumps[minJumps.length-1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 1, 0, 9};
        new MinNumberOfJumpsToReachEnd2().findDynamic(arr);

    }
}
