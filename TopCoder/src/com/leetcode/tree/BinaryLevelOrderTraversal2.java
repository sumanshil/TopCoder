package com.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

//https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
public class BinaryLevelOrderTraversal2 {

    public List<List<Integer>> find (TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        int height = getHeight(root);
        System.out.println(height);
        iterative(map, root, Integer.MAX_VALUE);
        List<List<Integer>> retVal =  new LinkedList<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            retVal.add(entry.getValue());
        }
        return retVal;
    }

    private void iterative(Map<Integer, List<Integer>> map, TreeNode root, int height) {
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> heightStack = new Stack<>();
        nodeStack.push(root);
        heightStack.push(height);
        while (!nodeStack.isEmpty()) {
            TreeNode top = nodeStack.pop();
            int topHeight = heightStack.pop();
            if (map.containsKey(topHeight)) {
                map.get(topHeight).add(top.val);
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(top.val);
                map.put(topHeight, list);
            }

            if (top.right != null){
                nodeStack.push(top.right);
                heightStack.push(topHeight-1);
            }
            if (top.left != null) {
                nodeStack.push(top.left);
                heightStack.push(topHeight-1);
            }
        }
    }

    private void recursive(Map<Integer, List<Integer>> map, TreeNode root, int height) {
        if (root == null) {
            return;
        }
        if (map.containsKey(height)) {
            map.get(height).add(root.val);
        } else {
            List<Integer> list = new LinkedList<>();
            list.add(root.val);
            map.put(height, list);
        }
        recursive(map, root.left, height-1);
        recursive(map, root.right, height-1);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lHeight = getHeight(root.left);
        int rHeight = getHeight(root.right);
        return Math.max(lHeight, rHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> list = new BinaryLevelOrderTraversal2().find(root);
        System.out.println(list);
    }
}
