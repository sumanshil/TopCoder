package com.leetcode.tree;

import com.geeksforgeeks.tree.BinaryTreeNode;

//https://leetcode.com/problems/longest-univalue-path/description/
public class LongestUnivaluePath {

    private int maxValue = Integer.MIN_VALUE;


    public void find (BinaryTreeNode root) {
        recursive(root);
        System.out.println(maxValue);
    }

    private int recursive(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            return 0;
        }
        int leftMax = recursive(root.getLeft());
        int rightMax = recursive(root.getRight());

        int current = 0;
        boolean leftIncluded = false;
        if (root.getLeft() != null && root.getLeft().getData() == root.getData()) {
            current = leftMax;
            leftIncluded = true;
        }
        if (root.getRight() != null && root.getRight().getData() == root.getData()) {
            if (leftIncluded) {
                maxValue = Math.max(maxValue, leftMax + rightMax+ 2);
            }
            current = Math.max(current, rightMax);
        }

        if (root.getLeft() != null && root.getLeft().getData() != root.getData()
                && root.getRight() == null) {
            return 0;
        }

        if (root.getRight() != null && root.getRight().getData() != root.getData()
            && root.getLeft() == null) {
            return 0;
        }

        if (root.getRight() != null && root.getRight().getData() != root.getData()
                && root.getLeft() != null && root.getLeft().getData() != root.getData()) {
            return 0;
        }
        if (current +1  > maxValue) {
            maxValue = current+1;
        }
        return current+1;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        new LongestUnivaluePath().find(root);
    }
}
