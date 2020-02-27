package com.leetcode;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/count-univalue-subtrees/
public class CountUniValueSubTree {
    private int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        traverse(root);
        return count;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        if (isValid(root, root.val)) {
            count++;
        }
        traverse(root.left);
        traverse(root.right);
    }

    private boolean isValid(TreeNode root, int val) {
        if (root == null) {
            return true;
        }
        if (root.val != val) {
            return false;
        }
        return isValid(root.left , val) && isValid(root.right, val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(5);

        int res = new CountUniValueSubTree().countUnivalSubtrees(root);
        System.out.println(res);
    }
}
