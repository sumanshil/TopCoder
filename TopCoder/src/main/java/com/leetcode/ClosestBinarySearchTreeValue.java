package com.leetcode;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/closest-binary-search-tree-value/
public class ClosestBinarySearchTreeValue {

    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }

        if (target < root.val ) {
            if (root.left != null && target < root.val && target > root.left.val) {
                double diff1 = root.val - target;
                double diff2 = target - root.left.val;
                if (diff1 > diff2) {
                    return root.left.val;
                } else {
                    return root.val;
                }
            } else if (root.left != null && target < root.val && target < root.left.val) {
                return closestValue(root.left, target);
            } else {
                return root.val;
            }
        } else {
            if (root.right != null && target > root.val && target < root.right.val) {
                double diff1 = target - root.val;
                double diff2 = root.right.val - target;
                if (diff1 > diff2) {
                    return root.right.val;
                } else {
                    return root.val;
                }
            } else if (root.right != null && target > root.val && target > root.right.val) {
                return closestValue(root.right, target);
            } else {
                return root.val;
            }

        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        int res = new ClosestBinarySearchTreeValue().closestValue(root,  3.714286);
        System.out.println(res);

    }
}
