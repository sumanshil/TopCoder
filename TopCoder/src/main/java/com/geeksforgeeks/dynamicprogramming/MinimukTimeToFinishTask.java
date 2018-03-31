package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 5/31/2016.
 */
//http://www.geeksforgeeks.org/minimum-time-to-finish-tasks-without-skipping-two-consecutive/
public class MinimukTimeToFinishTask {

    public void find(int[] arr){
        if (arr.length == 1){
            System.out.println(0);
            return;
        } else if (arr.length == 2){
            System.out.println(arr[0] < arr[1] ? arr[0] : arr[1]);
            return;
        } else if (arr.length == 3){
            System.out.println(arr[2] + arr[0] < arr[1] ? arr[2]+ arr[0] : arr[1]);
            return;
        }
        int[] minSum = new int[arr.length];
        minSum[0] = arr[0];
        minSum[1] = arr[1];
        //minSum[2] = arr[2] + arr[0] < arr[1] ? arr[2]+ arr[0] : arr[1];
        for ( int i = 2; i < arr.length ; i++){
            int minTime1 = arr[i] + minSum[i-2];
            int minTime2 = arr[i] + minSum[i-1];
            minSum[i] = minTime1 < minTime2 ? minTime1 : minTime2;
        }
        int result = minSum[minSum.length-1] < minSum[minSum.length-2] ? minSum[minSum.length-1] : minSum[minSum.length-2];
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[] arr = {10, 5,2,7, 10};
        new MinimukTimeToFinishTask().find(arr);
    }
}
