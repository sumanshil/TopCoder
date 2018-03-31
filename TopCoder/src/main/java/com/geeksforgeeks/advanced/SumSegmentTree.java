package com.geeksforgeeks.advanced;

public class SumSegmentTree {

    int st[];
    public void find (int arr[]) {
        int height = (int)(Math.log(arr.length)/Math.log(2));
        int totalSize = 2*(int)Math.pow(2, height)-1;
        st = new int[totalSize];
        constructStUtil(st, 0, arr.length-1, 0, arr);
    }

    private int constructStUtil(int[] st, int si, int se, int pos, int[] arr) {
        if (si == se) {
            st[pos] = arr[se];
            return st[pos];
        }
        int mid = si + (se - si)/2;
        int sum = constructStUtil(st, si, mid, 2*pos+1, arr)
                + constructStUtil(st, mid+1, se, 2*pos+2, arr);
        st[pos] = sum;
        return st[pos];
    }


    public void query(int arr[], int queryIndexStart, int queryIndexEnd) {
        int result = queryUtil(0,
                               arr.length-1,
                               queryIndexStart,
                               queryIndexEnd, 0);
        System.out.println(result);
    }

    private int queryUtil(int segmentTreeStartIndex,
                          int segmentTreeEndIndex,
                          int queryIndexStart,
                          int queryIndexEnd,
                          int segmentTreePos) {
        if (queryIndexStart <= segmentTreeStartIndex && queryIndexEnd >= segmentTreeEndIndex) {
            return st[segmentTreePos];
        }
        if (queryIndexEnd < segmentTreeStartIndex || queryIndexStart > segmentTreeEndIndex) {
            return 0;
        }

        int mid = segmentTreeStartIndex + (segmentTreeEndIndex - segmentTreeStartIndex)/2;
        int sum = queryUtil(segmentTreeStartIndex,
                            mid,
                            queryIndexStart,
                            queryIndexEnd,
                            2*segmentTreePos+1)+
                  queryUtil(segmentTreeStartIndex,
                            mid,
                            queryIndexStart,
                            queryIndexEnd,
                            2*segmentTreePos+2);
        return sum;
    }

    public static void main(String[] args) {

    }
}
