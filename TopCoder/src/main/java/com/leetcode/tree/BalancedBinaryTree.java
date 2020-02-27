package com.leetcode.tree;

//https://leetcode.com/problems/balanced-binary-tree/
public class BalancedBinaryTree {

    private boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        helper(root);
        return isBalanced;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (!isBalanced) {
            return 0;
        }

        int leftHeight = helper(root.left);
        int rightHeight = helper(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced = false;
            return 0;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        /*
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        //root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        //root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        //root.left.left.right = new TreeNode(4);
        root.left.left.left.left = new TreeNode(5);


        boolean result = new BalancedBinaryTree().isBalanced(root);
        System.out.println(result);
    }
}
