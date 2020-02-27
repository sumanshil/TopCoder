package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//
public class BinaryTreePostOrder {

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> list = new LinkedList<>();
        TreeNode prev = null;
        while (!stack.empty()) {
            TreeNode temp = stack.peek();
            if ((temp.left == null && temp.right == null) || (prev != null && (temp.left == prev || temp.right == prev))) {
                prev = stack.pop();
                list.add(temp.val);
                continue;
            }

            if (temp.right != null) {
                stack.push(temp.right);
            }

            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> list = new BinaryTreePostOrder().postorderTraversal(root);
        System.out.println(list);
    }
}
