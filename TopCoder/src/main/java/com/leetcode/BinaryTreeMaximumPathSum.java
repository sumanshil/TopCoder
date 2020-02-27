package com.leetcode;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/binary-tree-maximum-path-sum/
public class BinaryTreeMaximumPathSum {
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        recurisve(root);
        return maxSum;
    }

    private int recurisve(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lSum = recurisve(root.left);
        int rSum = recurisve(root.right);
        int totalValue = root.val + lSum+ rSum;
        if (totalValue < 0) {
            maxSum = Math.max(maxSum, totalValue);
            return 0;
        }
        maxSum = Math.max(maxSum, totalValue);
        return root.val + Math.max(lSum, rSum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1);

        int result = new BinaryTreeMaximumPathSum().maxPathSum(root);
        System.out.println(result);
    }
}
