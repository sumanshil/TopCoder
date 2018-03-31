package com.geeksforgeeks.segmenttree;

public class SegmentTree {

    int st[];

    public void constructSt(int[] arr) {
        int height = (int)Math.ceil(Math.sqrt(arr.length));
        int stSize = 2*(int)(Math.pow(2, height)) -1;
        st = new int[stSize];

        constructStUtil(arr, 0, arr.length-1, 0);

    }
    
    public void RMQ(int arr[], int low, int high){
        int result = RMQUtil (arr, 0, arr.length, low, high, 0);
        System.out.println(result);
    }

    private int RMQUtil(int[] arr, int ss, int se, int low, int high, int stPos) {
        if (low <= ss && high >= se) {
            return st[stPos];
        }

        if (high < ss || low > se){
            return Integer.MAX_VALUE;
        }

        int mid = getMid(ss, se);
        return Math.min(RMQUtil(arr,ss, mid, low, high, 2*stPos+1),
                        RMQUtil(arr,mid+1, high, low, high, 2*stPos+2));

    }

    private int constructStUtil(int[] arr, int ss, int se, int pos) {
        if (ss == se){
            st[pos] = arr[ss];
            return st[pos];
        }

        int mid = getMid(ss, se);
        int min = Math.min(constructStUtil(arr, ss, mid, 2*pos+1),
                  constructStUtil(arr, mid+1, se, 2*pos+1));
        st[pos] =min;
        return min;
    }

    private int getMid(int ss, int se) {
        return ss + (se - ss)/2;
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 7, 9, 11};
    }
}
