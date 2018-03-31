package com.leetcode.tree;

//https://leetcode.com/problems/diameter-of-binary-tree/description/
public class DiameterOfABinaryTree {


    public int diameterOfBinaryTree(TreeNode root) {
        int result = diameter(root);
        return result-1;
    }

    private int diameter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lHeight = height(root.left);
        int rHeight = height(root.right);
        int lDiameter = diameter(root.left);
        int rDiameter = diameter(root.right);
        return Math.max(lHeight + rHeight + 1, Math.max(lDiameter, rDiameter));

    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right))+1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        int result  = new DiameterOfABinaryTree().diameterOfBinaryTree(root);
        System.out.println(result);
    }

}
