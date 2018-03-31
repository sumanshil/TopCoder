package com.leetcode.tree;

//https://leetcode.com/problems/trim-a-binary-search-tree/description/
public class TrimABinarySearchTree {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        TreeNode root1 = trimRecursive(root, L, R);
        return root1;
    }

    private TreeNode trimRecursive(TreeNode root, int l, int r) {
        if (root == null) {
            return null;
        }
        if (root.val < l) {
            return trimRecursive(root.right, l, r);
        } else if (root.val > r) {
            return trimRecursive(root.left, l, r);
        } else {
            root.left = trimRecursive(root.left, l, r);
            root.right = trimRecursive(root.right, l, r);
        }
        return root;
    }


    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(0);
//        root.right = new TreeNode(2);
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);


        new TrimABinarySearchTree().trimBST(root, 3, 4);
    }

}
