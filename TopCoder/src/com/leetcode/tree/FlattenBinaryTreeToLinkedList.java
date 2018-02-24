package com.leetcode.tree;

import java.util.Stack;


//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
public class FlattenBinaryTreeToLinkedList {

    public void create(TreeNode node) {
        iterative(node);
    }

    private void iterative(TreeNode root) {
        TreeNode pre = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.right);
        stack.push(root.left);
        root.left = null;
        root.right = null;
        while (!stack.isEmpty() ) {
            TreeNode top = stack.pop();
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
            top.left = null;
            top.right = null;
            pre.right = top;
            pre = pre.right;
        }
    }

    private TreeNode recursive(TreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null && node.right == null) {
            return node;
        }

        TreeNode lNode = recursive(node.left);
        TreeNode rNode = recursive(node.right);
        TreeNode current = node;
        node.left = null;
        if (lNode != null) {
            lNode.left = null;
            current.right = lNode;
            while (current.right != null) {
                current = current.right;
            }
        }
        if (rNode != null) {
            rNode.left = null;
            current.right = rNode;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(6);
        new FlattenBinaryTreeToLinkedList().create(root);
    }

}
