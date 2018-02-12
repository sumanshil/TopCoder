package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/binary-tree-level-order-traversal/description/
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> traverse(TreeNode root) {
        if (root == null) {
            new ArrayList<>();
        }
        Stack<TreeNode> current = new Stack<>();
        Stack<TreeNode> child = new Stack<>();
        current.push(root);
        boolean leftToRight = false;
        List<List<Integer>> retVal = new ArrayList<>();

        while (!current.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            if (!leftToRight) {
                while (!current.isEmpty()) {
                    TreeNode node = current.pop();
                    list.add(node.val);
                    if (node.left != null){
                        child.push(node.left);
                    }
                    if (node.right != null) {
                        child.push(node.right);
                    }
                }
            } else {
                while (!current.isEmpty()) {
                    TreeNode node = current.pop();
                    list.add(node.val);
                    if (node.right != null){
                        child.push(node.right);
                    }
                    if (node.left != null) {
                        child.push(node.left);
                    }
                }
            }
            retVal.add(list);
            current = child;
            child = new Stack<>();
            leftToRight = !leftToRight;
        }
        return retVal;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        new BinaryTreeLevelOrderTraversal().traverse(root);
    }
}
