package com.leetcode;

import java.util.LinkedList;
import java.util.List;

import com.leetcode.tree.TreeNode;

public class LargestBst {

    private int maxValue = Integer.MIN_VALUE;
    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return maxValue == Integer.MIN_VALUE ? 0 : maxValue;
    }

    class RetVal {
        boolean isBst;
        int size;
        int max;
        int min;
        public RetVal(int size, boolean b, int max, int min) {
            isBst = b;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }
    private RetVal helper(TreeNode root) {
        if (root == null) {
            return new RetVal(0, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        if (root.left == null && root.right == null) {
            maxValue = Math.max(maxValue, 1);
            return new RetVal(1, true, root.val, root.val);
        }

        RetVal lVal = helper(root.left);
        RetVal rVal = helper(root.right);

        boolean isLeftBst = false;
        boolean isRightBst = false;

        if (root.left == null || (root.left.val < root.val && lVal.max < root.val)) {
            isLeftBst = true;
        }

        if (root.right == null || (root.right.val > root.val && rVal.min > root.val)) {
            isRightBst = true;
        }

        int max = Math.max(root.val, Math.max(lVal.max, rVal.max));
        int min = Math.min(root.val, Math.min(lVal.min, rVal.min));
        if (isLeftBst && isRightBst && rVal.min > lVal.max) {
            maxValue = Math.max(maxValue, lVal.size + rVal.size +1);
            return new RetVal(lVal.size + rVal.size + 1, true, max, min);
        } else {
            return new RetVal(lVal.size + rVal.size + 1, false, max, min);
        }
    }

    public static void main(String[] args) {

        /**
        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(5);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);

        root.right = new TreeNode(15);
        root.right.right = new TreeNode(7);
        **/

        /**
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        **/

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);


        int res = new LargestBst().largestBSTSubtree(root);
        System.out.println(res);
    }
}
