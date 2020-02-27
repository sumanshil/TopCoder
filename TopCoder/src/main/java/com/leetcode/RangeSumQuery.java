package com.leetcode;

import com.topcoder.problems.round13.Array;

public class RangeSumQuery {
    int[] dataArray;
    int[] sumArray;
    public RangeSumQuery(int[] nums) {
        dataArray = new int[nums.length];
        for (int i = 0 ; i < nums.length ; i++) {
            dataArray[i] = nums[i];
        }
        sumArray = new int[nums.length + 1];
        sumArray[0] = 0;
        for (int i = 1 ; i < sumArray.length ; i++) {
            sumArray[i] = sumArray[i-1] + nums[i-1];
        }

    }

    public void update(int i, int val) {
        int temp = dataArray[i];
        if (val > temp) {
            dataArray[i] = val;
            for (int j = i + 1 ; j < sumArray.length ; j++) {
                sumArray[j] += (val - temp);
            }
        } else {
            dataArray[i] = val;
            for (int j = i + 1 ; j < sumArray.length ; j++) {
                sumArray[j] -= (temp  - val);
            }
        }
    }

    public int sumRange(int i, int j) {
        return sumArray[j+1] - sumArray[i];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3 , 5};
        RangeSumQuery rs = new RangeSumQuery(arr);
        System.out.println(rs.sumRange(0, 2));
        rs.update(1, 2);
        System.out.println(rs.sumRange(0, 2));

    }
}
