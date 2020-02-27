package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/binary-tree-vertical-order-traversal/
public class BinaryTreeVerticalOrderTraversal {

    class Node {
        int level;
        TreeNode value;

        public Node(int level, TreeNode val) {
            this.level = level;
            this.value = val;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        /**
        Map<Integer, List<Integer>> map = new TreeMap<>();

        recursive(root, 0, map);
        List<List<Integer>> result = new LinkedList<>();

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
         **/
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, root));
        Map<Integer, List<Integer>> map = new TreeMap<>();
        List<List<Integer>> result = new LinkedList<>();

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            List<Integer> list = map.getOrDefault(node.level, new LinkedList<>());
            list.add(node.value.val);
            map.put(node.level, list);

            if (node.value.left != null) {
                queue.add(new Node(node.level - 1, node.value.left));
            }

            if (node.value.right != null) {
                queue.add(new Node(node.level + 1, node.value.right));
            }

        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;

    }

    private void recursive(TreeNode root, int level, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return;
        }
        List<Integer> list = map.getOrDefault(level, new LinkedList<>());
        list.add(root.val);
        map.put(level, list);

        recursive(root.left, level - 1, map);
        recursive(root.right, level + 1, map);
    }

    public static void main(String[] args) {
        /**
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
         **/
        /**
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(0);

        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(7);
        **/
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(0);
        root.left.right.right = new TreeNode(2);

        root.right.left = new TreeNode(1);
        root.right.left.left = new TreeNode(5);

        root.right.right = new TreeNode(7);

        List<List<Integer>> list = new BinaryTreeVerticalOrderTraversal().verticalOrder(root);
        System.out.println(list);

    }
}
