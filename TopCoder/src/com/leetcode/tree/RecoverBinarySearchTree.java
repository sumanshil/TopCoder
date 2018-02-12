package com.leetcode.tree;


//https://leetcode.com/problems/recover-binary-search-tree/description/
public class RecoverBinarySearchTree {

    private TreeNode firstNode = null;
    private TreeNode secondNode = null;
    private TreeNode pre = null;

    public void recoverTree(TreeNode root){
        morrisTraversal(root);
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    private void morrisTraversal(TreeNode root) {
        while (root != null) {
            if (root.left == null ){
                if (pre != null && pre.val >= root.val) {
                    if (firstNode == null) {
                        firstNode = pre;
                        secondNode = root;
                    } else {
                        secondNode = root;
                    }
                }
                pre = root;
                root = root.right;
            } else {
                TreeNode inorderSuccessor = getSuccessor(root, root.left);
                if (inorderSuccessor.right == null){
                    inorderSuccessor.right = root;
                    root = root.left;
                } else {
                    if (pre != null && pre.val >= root.val) {
                        if (firstNode == null) {
                            firstNode = pre;
                            secondNode = root;
                        } else {
                            secondNode = root;
                        }
                    }
                    inorderSuccessor.right = null;
                    pre = root;
                    root = root.right;
                }
            }
        }
    }

    private TreeNode getSuccessor(TreeNode root, TreeNode left) {
        while (left.right != null && left.right != root){
            left = left.right;
        }
        return left;
    }


    public static void main(String[] args) {
//        TreeNode root = new TreeNode(6);
//        root.left = new TreeNode(4);
//        root.left.left = new TreeNode(10);
//        root.left.right = new TreeNode(5);
//        root.right = new TreeNode(9);
//        root.right.left = new TreeNode(7);
//        root.right.right = new TreeNode(3);

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(8);

        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(20);

        root.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(-5);

        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(9);

        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(14);

        root.right.right.left = new TreeNode(18);
        root.right.right.right = new TreeNode(25);

        new RecoverBinarySearchTree().recoverTree(root);
    }

}
