package com.leetcode.tree;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToBst {

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = recursive(0, nums.length - 1, nums);
        return root;
    }

    private TreeNode recursive(int low, int high, int[] nums) {
        if (low > high) {
            return null;
        }

        int mid = low + (high - low)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recursive(low, mid - 1, nums);
        root.right = recursive(mid + 1, high, nums);
        return root;
    }


    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        TreeNode result = new ConvertSortedArrayToBst().sortedArrayToBST(nums);
        System.out.println(result);
    }
}
