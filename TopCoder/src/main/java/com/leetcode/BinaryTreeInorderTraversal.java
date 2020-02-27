package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/binary-tree-inorder-traversal/
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            TreeNode top = stack.pop();
            list.add(top.val);
            root = top.right;
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = new BinaryTreeInorderTraversal().inorderTraversal(root);
        list.stream().forEach(System.out::println);
    }
}
