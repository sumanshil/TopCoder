package com.leetcode.tree;

//https://leetcode.com/problems/recover-binary-search-tree/
public class RecoverBinarySerachTree {

    public void recoverTree(TreeNode root) {
        recursive(root);
    }

    private void recursive(TreeNode root) {
        if (root == null) {
            return;
        }
        int maxFromLeft = findMax(root.left);
        int minFromRight = findMin(root.right);

        if (root.val < maxFromLeft && root.val > minFromRight) {
            TravelAndEdit(root.left, maxFromLeft, minFromRight);
            TravelAndEdit(root.right, minFromRight, maxFromLeft);
            return;
        }

        if (root.val < maxFromLeft) {
            TravelAndEdit(root.left, maxFromLeft, root.val);
            root.val = maxFromLeft;
            return;
        }

        if (root.val > minFromRight) {
            TravelAndEdit(root.right, minFromRight, root.val);
            root.val = minFromRight;
            return;
        }

        recoverTree(root.left);
        recoverTree(root.right);
    }

    private void TravelAndEdit(TreeNode root, int target, int newVal) {
        if (root == null) {
            return;
        }

        if (root.val == target) {
            root.val = newVal;
            return;
        }
        TravelAndEdit(root.left, target, newVal);
        TravelAndEdit(root.right, target, newVal);
    }

    private int findMin(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int minL = findMin(root.left);
        int minR = findMin(root.right);

        return Math.min(Math.min(minL, minR), root.val);

    }

    private int findMax(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxL = findMax(root.left);
        int maxR = findMax(root.right);

        return Math.max(Math.max(maxL, maxR), root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        new RecoverBinarySerachTree().recoverTree(root);
    }
}
