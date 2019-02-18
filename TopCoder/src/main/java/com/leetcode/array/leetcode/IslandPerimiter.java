package com.leetcode.array.leetcode;

import com.geeksforgeeks.linkedlist.LinkedListNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//https://leetcode.com/problems/island-perimeter/description/
public class IslandPerimiter {

    public int islandPerimeter(int[][] grid) {

        int rowLength = grid.length;
        int colLength = grid[0].length;
        int result = 0;
        for ( int i = 0 ; i < grid.length ; i++) {
            for (int j = 0 ; j < grid[0].length ; j++) {
                if (grid[i][j] == 1) {
                    if (i + 1 >= rowLength || grid[i+1][j] == 0) {
                        result += 1;
                    }
                    if ( i - 1 < 0 || grid[i-1][j] == 0) {
                        result += 1;
                    }
                    if ( j + 1 >= colLength || grid[i][j+1] == 0) {
                        result += 1;
                    }
                    if ( j - 1 < 0 || grid[i][j-1] == 0) {
                        result += 1;
                    }
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        /*
        int[][] matrix = {{0,1,0,0},{1,1,1,0}, {0,1,0,0},{1,1,0,0}};
        int result = new IslandPerimiter().islandPerimeter(matrix);
        System.out.println(result);
        */
        //3,6,12,12,14,16,16,16,18,19,20,20,20,24,24,28,28,31
        //6,12,14,16,16,16,19,20,24,24,28,31
        int[] arr = {  3200};
        List<Integer> list = new LinkedList<>();
        for (int j = 0 ; j < arr.length ; j++) {
            for (int i = 1; i <= 3; i++) {
                double res = (Math.pow(arr[j], 2) + Math.pow(arr[j], 3)*i)%32;
                list.add((int)res);
            }
        }
        Collections.sort(list);
        String res = list.stream().map(i -> i+"").collect(Collectors.joining(","));
        System.out.println(res);
    }

}
