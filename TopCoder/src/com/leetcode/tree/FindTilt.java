package com.leetcode.tree;

//https://leetcode.com/problems/binary-tree-tilt/description/
public class FindTilt {

    private int tilt;

    public int findTilt(TreeNode root) {
        recursive(root);
        return tilt;
    }

    private int recursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lSum = recursive(root.left);
        int rSum = recursive(root.right);
        tilt += Math.abs(lSum - rSum);
        return lSum + root.val + rSum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int result = new FindTilt().findTilt(root);
        System.out.println(result);
    }

}
