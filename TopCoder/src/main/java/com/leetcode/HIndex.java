package com.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/h-index/
public class HIndex {

    public int hIndex(int[] citations) {

        for (int i = citations.length - 1 ; i >= 0 ; i--) {
            if (citations[i] == 0) {
                continue;
            }
            int length = citations.length - i;
            if (i - 1 >= 0 && citations[i-1] < length+1) {
                return length;
            } else if (i-1 < 0) {
                return length;
            }
        }
        return 0;

        /*
        int N = citations.length;
        int[] buckets = new int[N];
        for (int i = N-1 ; i >= 0; i++) {
            if (citations[i] >= N) {
                buckets[N] = buckets[N] + 1;
            } else {
                buckets[i] = buckets[i] + 1;
            }
        }

        int cnt = 0;
        for ( int i = N - 1 ; i >= 0 ; i--) {
            cnt += buckets[i];

            if (cnt >= i) {
                return i;
            }
        }
        return 0;
         */
    }

    public static void main(String[] args) {
        int[] arr = {0,1,3,5,6};
        int result = new HIndex().hIndex(arr);
        System.out.println(result);
    }
}
