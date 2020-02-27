package com.leetcode;

public class FindCelebrity {

    int[][] matrix = {
        {1, 1, 0},
        {0, 1, 0},
        {1, 1, 1}
    };
//    int[][] matrix = {
//        {1, 1},
//        {1, 1}
//    };
//        int[][] matrix = {
//            {1, 1, 0},
//            {0, 1, 1},
//            {0, 1, 1}
//        };

    public int findCelebrity(int n) {
        /**
        int[] inorder = new int[n];
        int[] outorder = new int[n];
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                // if i knows j
                if (i != j && knows(i, j)) {
                    inorder[j] = inorder[j] + 1;
                    outorder[i] = outorder[i] + 1;
                }
            }
        }
        int count = 0;
        int res = 0;
        for (int i = 0 ; i < n ; i++) {
            if (inorder[i] == n - 1 && outorder[i] == 0) {
                res = i;
                count++;
            }
        }
        if (count > 1 || count == 0) {
            return -1;
        }

        return res;
         **/
        int candidate = 0;
        for (int i = 0 ; i < n ; i++) {
            if ( i != candidate && knows(candidate, i)) {
                candidate = i;
            }
        }

        for (int i = 0 ; i < n ; i++) {
            if ( i != candidate && knows(candidate, i) && !knows(i, candidate)) {
                return -1;
            }
        }
        return 1;
    }

    boolean knows(int a, int b) {
        return  matrix[a][b] == 1;
    }

    public static void main(String[] args) {
        int n = 3;
        int res = new FindCelebrity().findCelebrity(n);
        System.out.println(res);
    }
}
