package com.leetcode.tree;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
public class BinaryTreeLevelOrderTraversal2 {

    /*
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        TreeMap<Integer, List<Integer>> newMap = new TreeMap<>((o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            } else {
                if (o1 < o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        recursive(root, newMap, 0);

        List<List<Integer>> result= new LinkedList<>();

        for (Map.Entry<Integer, List<Integer>> entry : newMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private void recursive(TreeNode root, TreeMap<Integer, List<Integer>> newMap, int level) {
        if (root == null) {
            return;
        }
        if (newMap.containsKey(level)) {
            newMap.get(level).add(root.val);
        } else {
            List<Integer> list = new LinkedList<>();
            list.add(root.val);
            newMap.put(level, list);
        }

        recursive(root.left, newMap, level + 1);
        recursive(root.right, newMap, level + 1);
    }
    */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        List<List<Integer>> result = new LinkedList<>();

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> newList = new LinkedList<>();
            for (int i = 0 ; i < size ; i++) {
                TreeNode currentNode = queue.remove();
                newList.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }

            }
            result.add(newList);
        }
        Collections.reverse(result);
        return result;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = new BinaryTreeLevelOrderTraversal2().levelOrderBottom(root);
        System.out.println(result);
    }
}
