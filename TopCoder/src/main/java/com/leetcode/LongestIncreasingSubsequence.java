package com.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = binarysearch(dp, 0, len, num);
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    private int binarysearch(int[] dp, int start, int end, int searchKey) {
        if (start > end) {
            return -1;
        }

        if (dp[start] == 0) {
            return start;
        }

        if (dp[start] > searchKey) {
            return start;
        }

        if (start == end) {
            if (dp[start] > searchKey) {
                return start;
            } else {
                return start + 1;
            }
        }

        int mid = start + (end - start)/2;
        if (dp[mid] < searchKey && dp[mid + 1] > searchKey) {
            return mid + 1;
        }
        if (mid -1 >= 0 && dp[mid - 1] < searchKey && dp[mid] > searchKey) {
            return mid;
        }

        if (dp[mid] < searchKey) {
            return binarysearch(dp, mid+1, end, searchKey);
        } else {
            return binarysearch(dp, start, mid-1, searchKey);
        }
    }

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        int res = new LongestIncreasingSubsequence().lengthOfLIS(arr);
        System.out.println(res);
    }
}
