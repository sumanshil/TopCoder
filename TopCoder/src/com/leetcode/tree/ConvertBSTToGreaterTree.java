package com.leetcode.tree;

//https://leetcode.com/problems/convert-bst-to-greater-tree/description/
public class ConvertBSTToGreaterTree {

    public TreeNode convertBST(TreeNode root) {
        recursive(root, 0);
        return root;
    }

    private int recursive(TreeNode root, int greaterSum) {
        if (root == null) {
            return 0;
        }

        int rSum = recursive(root.right, greaterSum);
        int val = root.val;
        root.val = rSum  + greaterSum +val;
        int lSum = recursive(root.left, val+rSum+greaterSum);
        return lSum + val + rSum;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(0);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(1);
        TreeNode treeNode = new ConvertBSTToGreaterTree().convertBST(root);
        System.out.println(treeNode);
    }

}
