package com.topcoder.problems.srm733;

import java.util.stream.Stream;

public class MinimizeAbsoluteDifferenceDiv2 {

    int[] findTriple(int x0, int x1, int x2) {

        float[] arr = {(float)x0, (float)x1, (float)x2};

        int[] result = new int[3];
        float miValue = Float.MAX_VALUE;

        for ( int i = 0 ; i < arr.length ; i++) {
            for ( int j = 0 ; j < arr.length ; j++) {
                for ( int k = 0 ; k < arr.length ; k++) {
                    if ( i != j && j != k && i != k ) {
                        float currentResult = Math.abs((arr[i]/arr[j])- arr[k]);
                        if (currentResult <= miValue) {
                            miValue = currentResult;
                            result[0] = i;
                            result[1] = j;
                            result[2] = k;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int result[] = new MinimizeAbsoluteDifferenceDiv2().findTriple(10, 11, 111);
        for ( int i = 0 ; i < result.length ; i++) {
            System.out.println(result[i]);
        }
    }
}
