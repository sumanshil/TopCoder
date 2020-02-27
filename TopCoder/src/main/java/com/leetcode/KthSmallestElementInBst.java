package com.leetcode;

import java.util.Stack;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class KthSmallestElementInBst {

    public int kthSmallest(TreeNode root, int k) {
        int currentCount = 1;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            TreeNode top = stack.pop();
            if (currentCount++ == k) {
                return top.val;
            }
            current = top.right;
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        int result = new KthSmallestElementInBst().kthSmallest(root, 3);
        System.out.println(result);
    }
}
