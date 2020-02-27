package com.leetcode;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/invert-binary-tree/
public class InvertBinaryTree {


    public TreeNode invertTree(TreeNode root) {
        return recursive(root);
    }

    private TreeNode recursive(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        TreeNode lNode = recursive(root.left);
        TreeNode rNode = recursive(root.right);

        root.left = rNode;
        root.right = lNode;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode result = new InvertBinaryTree().invertTree(root);

        System.out.println(result);

    }
}
