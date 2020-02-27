package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/path-sum/
public class PathSumTry1 {

    public boolean hasPathSum(TreeNode root, int sum) {
        boolean result = helper(root, 0, sum);
        return result;
    }

    private boolean helper(TreeNode root,int currentSum, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (currentSum + root.val == sum) {
                return true;
            }
            return false;
        }


        boolean lResult = helper(root.left, currentSum + root.val, sum);
        if (lResult) {
            return true;
        }
        boolean rResult = helper(root.right, currentSum + root.val, sum);
        if (rResult) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left .right= new TreeNode(2);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        /*
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
         */

        boolean result = new PathSumTry1().hasPathSum(root, 30);
        System.out.println(result);


    }
}
