package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-34-assembly-line-scheduling/
public class AssemblyLineScheduling {
    private static final int NUM_STATIONS = 4;

    public void find(int[][] a, int[][] t, int[] e, int[] x){
        int[] T1 = new int[NUM_STATIONS];
        int[] T2 = new int[NUM_STATIONS];
        T1[0] = a[0][0] + e[0];
        T2[0] = a[1][0] + e[1];
        for ( int i = 1 ; i < NUM_STATIONS ; i++ ) {
            T1[i] = a[0][i] + Math.min(T1[i-1], T2[i-1]+t[1][i]);
            T2[i] = a[1][i] + Math.min(T2[i-1], T1[i-1]+t[0][i]);
        }
        int result = Math.min(T1[T1.length-1]+x[0], T2[T2.length-1]+x[1]);
        System.out.println(result);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[][] a = {
            {4, 5, 3, 2},
            {2, 10, 1, 4},
        };
        int[][] t = {
            {0, 7, 4, 5},
            {0, 9, 2, 8}
        };
        int[] e = {10, 12};
        int[] x = {18, 7};
        new AssemblyLineScheduling().find(a, t, e, x);
    }

}
