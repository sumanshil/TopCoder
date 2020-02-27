package com.leetcode;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/summary-ranges/
public class SummaryRanges {


    public List<String> summaryRanges(int[] nums) {
        int start = nums[0];
        int end = nums[0];
        List<String> list = new LinkedList<>();
        for (int i = 1 ; i < nums.length ; i++) {
            if (nums[i] - nums[i-1] == 1) {
                end = nums[i];
            } else {
                if (start != end) {
                    list.add(start + "->" + end);
                } else {
                    list.add(start+"");
                }
                start = nums[i];
                end = nums[i];
            }
        }
        if (start != end) {
            list.add(start + "->" + end);
        } else {
            list.add(start+"");
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {0,2,3,4,6,8,9};
        List<String> result = new SummaryRanges().summaryRanges(arr);
        System.out.println(result);
    }
}
