package com.geeksforgeeks.advanced;

public class RangeSumSegmentTree {
    int st[] ;
    public void constructST(int arr[]) {
        int n = arr.length;
        int height = (int)Math.ceil(Math.log(n)/Math.log(2));
        int totalLength = 2* (int)Math.pow(2, height) - 1;
        st = new int[totalLength];
        constructSTUtil(arr, 0, n-1, 0);
    }


    public int query (int queryStartIndex, int queryEndIndex, int[] arr) {
        int result = queryUtil(arr, 0, arr.length-1, queryStartIndex, queryEndIndex, 0);
        System.out.println(result);
        return result;
    }

    private int queryUtil(int[] arr, int ss, int se, int queryStartIndex, int queryEndIndex, int pos) {
        if (queryStartIndex <= ss && queryEndIndex >= se){
            return st[pos];
        }
        if (se < queryStartIndex || ss > queryEndIndex) {
            return 0;
        }

        int mid = getMid(ss, se);
        return queryUtil(arr, ss, mid, queryStartIndex, queryEndIndex, 2*pos+1)
                + queryUtil(arr, mid + 1, se, queryStartIndex, queryEndIndex, 2*pos+2);
    }

    private int constructSTUtil(int[] arr, int low, int high, int pos) {
        if (low == high) {
            st[pos] = arr[low];
            return arr[low];
        }

        int mid = getMid(low, high);
        int sum = constructSTUtil(arr, low, mid, 2*pos + 1)
                + constructSTUtil(arr, mid + 1,high, 2*pos + 2);
        st[pos] = sum;
        return sum;
    }

    private int getMid(int low, int high) {
        return (low) + (high - low)/2;
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4};
        RangeSumSegmentTree rst = new RangeSumSegmentTree();
        rst.constructST(arr);
        int sum = rst.query(0, 2, arr);
        System.out.println(sum);
    }

}
