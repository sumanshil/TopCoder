package com.geeksforgeeks.tree.niary;

import com.geeksforgeeks.tree.BinaryTreeNode;

public class LowestCommonAncestorInBinaryTree {

    private int matrix[][];
    private boolean found = false;

    public void find (BinaryTreeNode root, int data1, int data2) {
        matrix = new int[2][10];
        findRecursive(root, 0, 0, data1);
        found = false;
        findRecursive(root, 1, 0, data2);
        int column1 = 0;
        int column2 = 0;
        int result = 0;
        while (column1 < 10 && column2 < 10){
            if (matrix[0][column1] == matrix[1][column2]){
                result = matrix[0][column2];
                column1++;
                column2++;
            } else {
                break;
            }
        }
        System.out.println(result);
    }

    private void findRecursive(BinaryTreeNode root, int row, int column, int data) {
        if (root == null) {
            return;
        }

        if (found) {
            return;
        }
        if (root.getData() == data){
            matrix[row][column] = data;
            found = true;
            return;
        }

        matrix[row][column] = root.getData();
        findRecursive(root.getLeft(), row, column+1, data);
        findRecursive(root.getRight(), row, column+1, data);

    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);

        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);

        root.getRight().insertInLeft(6);
        root.getRight().insertInRight(7);
        new LowestCommonAncestorInBinaryTree().find(root, 4, 7);
    }
}
