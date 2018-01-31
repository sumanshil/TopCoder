package com.leetcode.tree;

//https://leetcode.com/problems/unique-binary-search-trees/description/
public class UniqueBinarySerachTrees1 {
    int matrix[][] = null;
    public int numTrees(int n) {
        matrix = new int[n+1][n+1];
        for (int i = 0 ; i <=n ; i++){
            for ( int j = 0 ; j <= n ; j++){
                matrix[i][j] = -1;
            }
        }
        int result = 0;
        for (int i = 1 ; i <=n ; i++) {
            int lCount = recursive(1, i-1);
            int rCount = recursive(i+1, n);
            result += (lCount * rCount);
        }
        return result;
    }

    private int recursive(int low, int high) {
        if (low >= high){
            return 1;
        }
        if (matrix[low][high] != -1){
            return matrix[low][high];
        }
        int result = 0;
        for (int i = low ; i <=high ; i++) {
            int lCount = recursive(low, i-1);
            int rCount = recursive(i+1, high);
            result += (lCount * rCount);
        }
        matrix[low][high] = result;
        return result;
    }

    static abstract class  A  implements Cloneable {
        int x;
        A(int x){
            this.x = x;
        }
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    static class B extends A {
        B(int x){
            super(x);
        }
        int y;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        //int result = new UniqueBinarySerachTrees1().numTrees(3);
        //System.out.println(result);
        B b = new B(1);
        b.y = 2;
        B b1 = (B)b.clone();
        System.out.println(b1);
    }
}
