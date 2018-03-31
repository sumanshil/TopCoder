package com.leetcode.tree;

public class BinaryTreeDeepCopy {


    public void deepCopy(TreeNode root){
        TreeNode copied = deepCopyRecursive(root);
        System.out.println(copied);
    }

    private TreeNode deepCopyRecursive(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode lNode = deepCopyRecursive(root.left);
        TreeNode rNode = deepCopyRecursive(root.right);
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = lNode;
        newNode.right = rNode;
        return newNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        new BinaryTreeDeepCopy().deepCopy(root);
    }

}
