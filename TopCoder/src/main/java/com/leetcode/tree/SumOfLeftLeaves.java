package com.leetcode.tree;

public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        int sum = recursiveSum(root, false);
        return sum;
    }

    private int recursiveSum(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            if (isLeft) {
                return root.val;
            } else {
                return 0;
            }
        }
        int lSum = recursiveSum(root.left, true);
        int rSum = recursiveSum(root.right, false);
        return lSum + rSum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        int sum = new SumOfLeftLeaves().sumOfLeftLeaves(root);
        System.out.println(sum);
    }


}
