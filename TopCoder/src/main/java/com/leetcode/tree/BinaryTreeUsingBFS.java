package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeUsingBFS {

    public TreeNode clone(TreeNode root) {
        TreeNode newRoot = new TreeNode(root.val);
        Queue<TreeNode> originalQueue = new LinkedList<>();

        Queue<TreeNode> newParentQueue = new LinkedList<>();
        Queue<TreeNode> newChildQueue = new LinkedList<>();
        newParentQueue.add(newRoot);
        originalQueue.add(root);
        while (!originalQueue.isEmpty()){
            int size = originalQueue.size();
            for ( int i = 0 ; i < size ; i++) {
                TreeNode node = originalQueue.remove();
                TreeNode newNode = newParentQueue.remove();
                if (node != null){
                    System.out.println(node.val);
                    TreeNode lNode = node.left;
                    TreeNode rNode = node.right;

                    if (lNode != null) {
                        originalQueue.add(lNode);
                        TreeNode newLNode = new TreeNode(lNode.val);
                        newNode.left = newLNode;
                        newChildQueue.add(newLNode);
                    } else {
                        originalQueue.add(null);
                        newNode.left = null;
                        newChildQueue.add(null);
                    }

                    if (rNode != null) {
                        originalQueue.add(rNode);
                        TreeNode newRNode = new TreeNode(rNode.val);
                        newNode.right = newRNode;
                        newChildQueue.add(newRNode);
                    } else {
                        originalQueue.add(null);
                        newNode.right = null;
                        newChildQueue.add(null);
                    }
                }
            }
            newParentQueue = newChildQueue;
        }
        inOrder(root);
        System.out.println();
        inOrder(newRoot);
        return newRoot;
    }

    private void inOrder(TreeNode root){
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val);
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(9);
        TreeNode newRoot = new BinaryTreeUsingBFS().clone(root);
        System.out.println(newRoot);
    }
}
