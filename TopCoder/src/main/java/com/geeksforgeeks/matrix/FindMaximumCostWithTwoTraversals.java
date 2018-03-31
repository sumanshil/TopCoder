package com.geeksforgeeks.matrix;

/**
 * Created by sshil on 6/13/2016.
 */
//http://www.geeksforgeeks.org/collect-maximum-points-in-a-grid-using-two-traversals/
public class FindMaximumCostWithTwoTraversals {

    int[][] matrix = {
        {3, 6, 8, 2},
        {5, 2, 4, 3},
        {1, 1, 20, 10},
        {1, 1, 20, 10},
        {1, 1, 20, 10}
    };

    public void find(){
        int result = findRecursive(0,0,matrix[0].length-1);
        System.out.println(result);
    }

    int[][][] memo = new int[matrix.length][matrix[0].length][matrix[0].length];

    private void findDynamic(){
        int result = maxFindingUtil(0, 0, matrix[0].length-1);
        System.out.println(result);
    }

    private int maxFindingUtil(int x1, int y1, int y2) {
        if ( x1 == matrix.length-1 && y1 == 0 && y2 == matrix[0].length-1) {
            return matrix[x1][y1] + matrix[x1][y2];
        }

        if (!isValid(x1, y1, y2)){
            return Integer.MIN_VALUE;
        }

        if (memo[x1][y1][y2] != 0){
            return memo[x1][y1][y2];
        }

        int temp = 0;
        if (y1 == y2){
            temp += matrix[x1][y1];
        } else {
            temp += matrix[x1][y1] + matrix[x1][y2];
        }

        int ans = Integer.MIN_VALUE;
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1, y2+1));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1, y2-1));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1, y2));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1-1, y2+1));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1-1, y2-1));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1-1, y2));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1+1, y2+1));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1+1, y2-1));
        ans = Math.max(ans,temp+maxFindingUtil(x1+1, y1+1, y2));

        memo[x1][y1][y2] = ans;
        return ans;
    }


    private int findRecursive(int x1, int y1, int y2) {
        if (x1 == matrix.length-1 && y1 == 0 && y2 == matrix[0].length-1){
            return matrix[x1][y1] + matrix[x1][y2];
        }

        if (!isValid(x1, y1, y2)){
            return Integer.MIN_VALUE;
        }

        int result = 0;
        if (y1 == y2){
            result += matrix[x1][y1];
        } else {
            result += matrix[x1][y1]+matrix[x1][y2];
        }

        int[] arr = new int[9];
        arr[0] = findRecursive(x1+1, y1, y2+1);
        arr[1] = findRecursive(x1+1, y1, y2-1);
        arr[2] = findRecursive(x1+1, y1, y2);
        arr[3] = findRecursive(x1+1, y1-1, y2+1);
        arr[4] = findRecursive(x1+1, y1-1, y2-1);
        arr[5] = findRecursive(x1+1, y1-1, y2);
        arr[6] = findRecursive(x1+1, y1+1, y2+1);
        arr[7] = findRecursive(x1+1, y1+1, y2-1);
        arr[8] = findRecursive(x1+1, y1+1, y2);
        int max = findMax(arr);
        return max + result;
    }

    private int findMax(int[] arr) {
        int result = Integer.MIN_VALUE;
        for ( int i : arr){
            if ( i > result){
                result = i;
            }
        }
        return result;
    }

    private boolean isValid(int x1, int y1, int y2) {
        return x1 >= 0 && x1 < matrix.length && y1 >= 0 && y1 < matrix[0].length && y2 >= 0 && y2 < matrix[0].length;
    }


    public static void main(String[] args) {
        new FindMaximumCostWithTwoTraversals().findDynamic();
    }
}
