package com.leetcode;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
public class LomngestConsecutive {
    int max = Integer.MIN_VALUE;
    public int longestConsecutive(TreeNode root) {
        recursive(root);
        return max;
    }

    private int recursive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int lHeight = recursive(root.left);
        int rHeight = recursive(root.right);
        int maxVal = 1;
        if (root.left != null && root.left.val - root.val == 1) {
            maxVal = Math.max(maxVal, lHeight+1);
        }

        if (root.right != null && root.right.val - root.val == 1) {
            maxVal = Math.max(maxVal, rHeight+1);
        }
        max = Math.max(maxVal, max);
        return maxVal;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.left = new TreeNode(2);
        int result = new LomngestConsecutive().longestConsecutive(root);
        System.out.println(result);

    }
}
