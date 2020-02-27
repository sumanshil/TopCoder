package com.leetcode.tree;

import java.util.List;

//https://leetcode.com/problems/sum-root-to-leaf-numbers/submissions/

public class SumRootToLeaf {
    private int sum = 0;

    public int sumNumbers(TreeNode root) {
        recursive(root, 0);
        return sum;
    }

    private void recursive(TreeNode root, int current) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            sum +=  (current*10 + root.val);
            return;
        }

        recursive(root.left, current*10 + root.val);
        recursive(root.right, current*10 + root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int result = new SumRootToLeaf().sumNumbers(root);
        System.out.println(result);
    }
}
