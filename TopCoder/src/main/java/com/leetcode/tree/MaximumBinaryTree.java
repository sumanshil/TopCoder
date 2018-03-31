package com.leetcode.tree;

import com.geeksforgeeks.tree.BinaryTreeNode;

//https://leetcode.com/problems/maximum-binary-tree/description/
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = recursive(nums, 0, nums.length-1);
        return root;
    }

    private TreeNode recursive(int[] nums, int low, int high) {
        if (high < low) {
            return null;
        }
        if (high == low) {
            return new TreeNode(nums[high]);
        }
        int maxIndex = findMaximum(nums, low, high);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = recursive(nums, low, maxIndex-1);
        root.right = recursive(nums, maxIndex+1, high);
        return root;
    }

    private int findMaximum(int[] nums, int low, int high) {
        int index = low;
        int max = Integer.MIN_VALUE;
        for (int i = low ; i <= high ; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,1,6,0,5};
        TreeNode root = new MaximumBinaryTree().constructMaximumBinaryTree(arr);
    }

}
