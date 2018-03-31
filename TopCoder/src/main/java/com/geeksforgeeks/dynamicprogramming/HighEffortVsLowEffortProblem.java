package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/29/2016.
 */
//http://www.geeksforgeeks.org/dynamic-programming-high-effort-vs-low-effort-tasks-problem/
public class HighEffortVsLowEffortProblem {

    public void find(int[] le, int[] he){
        int[] maxEffort = new int[le.length+1];
        maxEffort[0] = 0;
        maxEffort[1] = he[0];
        for ( int i = 2 ; i < maxEffort.length ; i++) {
            int index = i-1;
            int effort1 = he[index]+ maxEffort[i-2];
            int effort2 = le[index] + maxEffort[i-1];
            maxEffort[i] = Math.max(effort1, effort2);
        }
        int result = maxEffort[maxEffort.length-1];
        System.out.println(result);

    }
    public static void main(String[] args) {
        int[] le = {1, 5, 4,5 , 3};
        int[] he = {3, 6, 8, 7, 6};
        new HighEffortVsLowEffortProblem().find(le, he);
    }
}
