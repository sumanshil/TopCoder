package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/6/17.
 */
//http://www.geeksforgeeks.org/painting-fence-algorithm/
public class PaintingFenceAlgo {

    // n = 1
    // diff = k
    // same = 0

    // n = 2
    // diff = (k* (k-1))
    // same = k
    // total = (k*(k-1)) + k

    // n = 3
    // diff = [(k*(k-1)) + k]*(k-1)
    // same = k*(k-1)

    // same(i) = diff(i-1)
    // diff(i) = (total(i-1))*(k-1)
    // total(i) = diff(i) + diff(i-1)

    public int find(int n , int k){
        int[] diff = new int[n+1];
        int[] total = new int[n+1];

        diff[1] = k;
        total[1] = k;

        diff[2] = (k*(k-1));
        total[2] = diff[2] + diff[1];

        for ( int i = 3 ; i <= n ; i++) {
            diff[i] = total[i-1]*(k-1);
            total[i] = diff[i] + diff[i-1];
        }

        return total[n];
    }


    public static void main(String[] args) {
        int n = 2;
        int k = 4;
        int result = new PaintingFenceAlgo().find(n, k);
        System.out.println(result);
    }
}
