package com.leetcode.tree;

import java.util.Stack;

public class CreateBSTFromSortedArray {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            return null;
        }
    //    TreeNode root = recursive(nums, 0, nums.length-1);
        TreeNode root = iterative(nums);
        return root;
    }

    private TreeNode iterative(int[] nums) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> leftIndexStack = new Stack<>();
        Stack<Integer> rightIndexStack = new Stack<>();
        TreeNode root = new TreeNode(0);
        nodeStack.push(root);
        leftIndexStack.push(0);
        rightIndexStack.push(nums.length-1);
        while (!nodeStack.isEmpty()) {
            TreeNode currentNode = nodeStack.pop();
            int low = leftIndexStack.pop();
            int high = rightIndexStack.pop();
            int mid = low + (high - low)/2;
            currentNode.val = nums[mid];
            if (low < mid) {
                currentNode.left = new TreeNode(0);
                nodeStack.push(currentNode.left);
                leftIndexStack.push(low);
                rightIndexStack.push(mid-1);
            }
            if (mid < high) {
                currentNode.right = new TreeNode(0);
                nodeStack.push(currentNode.right);
                leftIndexStack.push(mid+1);
                rightIndexStack.push(high);
            }
        }
        return root;
    }

    private TreeNode recursive(int[] nums, int low, int high) {
        if (low == high) {
            return new TreeNode(nums[low]);
        }
        if (low + 1 == high) {
            TreeNode newRoot = new TreeNode(nums[high]);
            newRoot.left = new TreeNode(nums[low]);
            return newRoot;
        }

        int mid = low + (high - low)/2;
        TreeNode newRoot = new TreeNode(nums[mid]);
        newRoot.left = recursive(nums, low, mid-1);
        newRoot.right = recursive(nums, mid + 1, high);
        return newRoot;
    }


    public static void main(String[] args) {
        int[] arr = {-10,-3,0,5,9};
        TreeNode node = new CreateBSTFromSortedArray().sortedArrayToBST(arr);
    }
}
