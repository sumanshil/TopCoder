package com.leetcode.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

//https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
public class FindLargestValueInEachTree {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0 ; i < size ; i++) {
                TreeNode node = queue.remove();
                max = Math.max(max, node.val);
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(max);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        List<Integer> list = new FindLargestValueInEachTree().largestValues(root);
        list.stream().forEach(System.out::print);
    }
}
