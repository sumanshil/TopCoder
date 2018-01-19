package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.geeksforgeeks.tree.BinaryTreeNode;

//https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
public class AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new LinkedList<>();
        bfs(root, list);
        return list;
    }

    private void bfs(TreeNode root, List<Double> list) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0 ; i < size ; i++) {
                TreeNode node = queue.remove();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            double result = sum / size;
            list.add(result);
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        new AverageOfLevelsInBinaryTree().averageOfLevels(root);
    }
}
