package com.leetcode;

import com.leetcode.tree.TreeNode;

public class SymmetricTree {


    public boolean isSymmetric(TreeNode root) {
        boolean result = recursive(root.left, root.right);
        return result;
    }

    private boolean recursive(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if ((root1 == null) || (root2 == null)) {
            return false;
        }

        return root1.val == root2.val
               && recursive(root1.left, root2.right)
               && recursive(root1.right, root2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
    }
}
