package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
public class BinaryTreeZigZag {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(result, 0, root);
        return result;
    }

    private void dfs(List<List<Integer>> result, int level, TreeNode root) {
        if (root == null) {
            return;
        }

        if (level == result.size()) {
            result.add(new ArrayList<>());
        }

        if (level % 2 == 0) {
            result.get(level).add(root.val);
        } else {
            result.get(level).add(0, root.val);
        }
        dfs(result, level+1, root.left);
        dfs(result, level+1, root.right);
    }

    /*
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean leftToRight = false;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        List<List<Integer>> result = new LinkedList<>();

        while (true) {
            if (stack1.isEmpty()) {
                break;
            }
            List<Integer> newList = new LinkedList<>();

            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                newList.add(node.val);
                if (leftToRight) {
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                } else {
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                }
            }
            result.add(newList);
            leftToRight = !leftToRight;
            stack1 = stack2;
            stack2 = new Stack<>();
        }
        return result;
    }

     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> res = new BinaryTreeZigZag().zigzagLevelOrder(root);
        for (List<Integer> integers : res) {
            System.out.println(integers);
        }
    }
}
