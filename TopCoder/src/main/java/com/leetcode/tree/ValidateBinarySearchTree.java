package com.leetcode.tree;

//https://leetcode.com/problems/validate-binary-search-tree/submissions/
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        boolean result = recursiveCheck(root);
        return result;
    }

    private boolean recursiveCheck(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean isLeftValid = recursiveCheck(root.left);
        boolean isRightValid = recursiveCheck(root.right);

        if (isLeftValid && (root.left == null || root.val > root.left.val)
            && (root.right == null || root.val < root.right.val) && isRightValid) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        boolean result = new ValidateBinarySearchTree().isValidBST(root);
        System.out.println(result);
    }
}
