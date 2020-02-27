package com.leetcode;

//https://leetcode.com/problems/first-bad-version/
public class FirstBadVersion {

    public int firstBadVersion(int n) {
        if (n == Integer.MAX_VALUE) {
            return -1;
        }
        int result = findRecursive(1, n, n);
        return result;
    }

    private int findRecursive(int low, int high, int max) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low)/2;

        if (isBadVersion(mid, max) && !isBadVersion(mid-1, max) && isBadVersion(mid+1, max)) {
            return mid;
        }

        if (isBadVersion(mid, max) && isBadVersion(mid-1, max) && isBadVersion(mid+1, max)) {
            return findRecursive(low, mid - 1, max);
        } else {
            return findRecursive(mid + 1, high, max);
        }
    }

    private boolean isBadVersion(int mid, int max) {
        return mid <= max && (mid == 5 || mid == 4);
    }

    public static void main(String[] args) {
        int n = 5;
        int res = new FirstBadVersion().firstBadVersion(Integer.MAX_VALUE);
        System.out.println(res);
    }
}
