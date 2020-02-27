package com.leetcode;

import org.omg.PortableInterceptor.INACTIVE;

//https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/
public class VerifyPreOrderSequence {

    public boolean verifyPreorder(int[] preorder) {
        if (preorder.length == 0) {
            return true;
        }
        boolean result = recursive(0, preorder.length-1, Integer.MIN_VALUE, Integer.MAX_VALUE, preorder);
        return result;
    }

    private boolean recursive(int start, int end, int max, int min, int[] arr) {
        if (start > end) {
            return true;
        }

        if (start == end) {
            if (max != Integer.MIN_VALUE) {
                if (arr[start] > max) {
                    return false;
                }
            }

            if (min != Integer.MAX_VALUE) {
                if (arr[start] < min) {
                    return false;
                }
            }
            return true;
        }
        int nextGreaterIndex = -1;
        for (int i = start ; i <= end ; i++) {
            if (max != Integer.MIN_VALUE && arr[i] > max) {
                return false;
            }
            if (min != Integer.MAX_VALUE && arr[i] < min) {
                return false;
            }
            if (arr[i] > arr[start]) {
                nextGreaterIndex = i;
                break;
            }

        }
        if (nextGreaterIndex != -1) {
            boolean lFound = recursive(start + 1, nextGreaterIndex - 1, arr[start], min, arr);
            boolean rFound = recursive(nextGreaterIndex, end, max, arr[start], arr);
            return lFound && rFound;
        } else {
            boolean lFound = recursive(start + 1, end, arr[start], min, arr);
            return lFound;
        }
    }

    public static void main(String[] args) {
        //int[] arr = {5, 2, 6, 1, 3};
        //int[] arr = {5, 2, 1, 3, 6};
        //int[] arr = {1, 2, 3,4};
        //int[] arr = {3, 2, 1};
        //int[] arr = {2, 1, 3};
        //int[] arr = {1, 2, 4, 3};
        //int[] arr = {1, 3, 4, 2};
        int[] arr = {4, 2, 3, 1};
        // false

        //int[] arr = {1, 3, 2};
        boolean result = new VerifyPreOrderSequence().verifyPreorder(arr);
        System.out.println(result);
    }
}
