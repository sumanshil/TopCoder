package com.leetcode.tree;

import java.util.Stack;

//https://leetcode.com/problems/validate-binary-search-tree/discuss/32112
public class InOrderTraversal {

    public void inOrderIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode top = stack.pop();
            System.out.println(top.val);
            root = top.right;
        }
    }

    public void morrisTraversal(TreeNode root) {
        while (root != null) {
            if (root.left == null){
                System.out.println(root.val);
                root = root.right;
            } else {
                TreeNode inOrderSuccessor = getSuccessor(root, root.left);
                if (inOrderSuccessor.right == null){
                    inOrderSuccessor.right = root;
                    root = root.left;
                } else {
                    System.out.println(root.val);
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
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        new InOrderTraversal().morrisTraversal(root);
    }

}
