package com.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/triangle/
public class Triangle {

    public int minimumTotal1(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.get(triangle.size()-1).size()];


        for (int i = 0 ; i < triangle.get(triangle.size()-1).size() ; i++) {
            dp[i] = triangle.get(triangle.size()-1).get(i);
        }

        for (int j = triangle.size()-2 ; j >= 0 ; j--) {
            for (int i = 0; i < triangle.get(j).size(); i++) {
                dp[i] = triangle.get(j).get(i) + Math.min(dp[i], dp[i+1]);
            }
        }

        return dp[0];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> result = new LinkedList<>();
        result.add(triangle.get(0).get(0));
        int finalResult = 0;
        for (int i = 1 ; i < triangle.size() ; i++) {
            List<Integer> result1 = new LinkedList<>();
            int min = Integer.MAX_VALUE;
            int value = 0;
            for (int j = 0 ; j < triangle.get(i).size() ; j++) {
                if (j == 0) {
                    value = triangle.get(i).get(j) + result.get(j);
                    result1.add(value);
                } else if (j == triangle.get(i).size() - 1) {
                    value = triangle.get(i).get(j) + result.get(j-1);
                    result1.add(value);
                } else {
                    int value1 = triangle.get(i).get(j) + result.get(j-1);
                    int value2 = triangle.get(i).get(j) + result.get(j);
                    value = Math.min(value1, value2);
                    result1.add(value);
                }
                min = Math.min(value, min);
                finalResult = min;
            }
            result = result1;
        }

        return finalResult;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new LinkedList<>();
        list.add(Collections.singletonList(2));
        list.add(Arrays.asList(3,4));
        list.add(Arrays.asList(6,5,7));
        list.add(Arrays.asList(4,1,8,3));

        int result = new Triangle().minimumTotal1(list);
        System.out.println(result);
    }
}
