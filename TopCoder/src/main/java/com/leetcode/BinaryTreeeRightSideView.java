package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.leetcode.tree.TreeNode;

public class BinaryTreeeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0 ; i < size ; i++) {
                TreeNode node = queue.remove();
                if ( i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        List<Integer> result = new BinaryTreeeRightSideView().rightSideView(root);
        System.out.println(result);
    }
}
