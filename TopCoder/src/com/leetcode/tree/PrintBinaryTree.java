package com.leetcode.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/print-binary-tree/description/
public class PrintBinaryTree {

    public  List<List<String>>  printTree(TreeNode root) {
        int height = getHeight(root);
        int rowNumber = height;
        int colNumber = (int)Math.pow(2, height) - 1;
        String matrix[][] = new String[rowNumber][colNumber];
        for ( int i = 0 ; i < matrix.length ; i++){
            for (int j = 0; j < matrix[0].length ; j++) {
                matrix[i][j] = "";
            }
        }
        recursive(matrix, rowNumber, colNumber, 0, 0, colNumber, root);
        List<List<String>> result = new LinkedList<>();

        for ( int i = 0 ; i < rowNumber ; i++) {
            String[] row = matrix[i];
            result.add(Arrays.asList(row));
        }
        return result;
    }

    private void recursive(String[][] matrix,
                           int rowNumber,
                           int colNumber,
                           int currentRow,
                           int lowCol,
                           int highCol,
                           TreeNode root) {
        if (currentRow > rowNumber) {
            return;
        }

        if (highCol < lowCol) {
            return;
        }
        if (root == null) {
            return;
        }
        int mid = (highCol + lowCol)/2;
        matrix[currentRow][mid] = root.val+"";
        recursive(matrix,
                  rowNumber,
                  colNumber,
                  currentRow + 1,
                  lowCol,
                  mid-1,
                  root.left);
        recursive(matrix,
                  rowNumber,
                  colNumber,
                  currentRow + 1,
                  mid+1,
                  highCol,
                  root.right);

    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);
        return Math.max(lHeight, rHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);

        List<List<String>> res = new PrintBinaryTree().printTree(root);
        System.out.println(res);
    }

}
