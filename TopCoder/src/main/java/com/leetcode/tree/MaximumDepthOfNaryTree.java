package com.leetcode.tree;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MaximumDepthOfNaryTree {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Stack<Node> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();

        nodeStack.push(root);
        depthStack.push(1);
        int max = Integer.MIN_VALUE;

        while (!nodeStack.isEmpty()) {
            Node node = nodeStack.pop();
            int depth = depthStack.pop();
            max = Math.max(max, depth);
            for (Node child : node.children) {
                depthStack.push(depth + 1);
                nodeStack.push(child);
            }
        }
        return max;
    }

    public int maxDepthRecursive(Node root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = Integer.MIN_VALUE;

        for (Node child : root.children) {
            maxDepth = Math.max(maxDepth, maxDepthRecursive(child));
        }
        return maxDepth == Integer.MIN_VALUE ? 1 : maxDepth + 1;
    }

    public static void main(String[] args) {
        Node node5 = new Node(5, Collections.emptyList());
        Node node6 = new Node(6, Collections.emptyList());

        Node node3 = new Node(3, Arrays.asList(node5, node6));
        Node node2 = new Node(2, Collections.emptyList());
        Node node4 = new Node(4, Collections.emptyList());

        Node node1 = new Node(1, Arrays.asList(node3, node2, node4));

        int maxDepth = new MaximumDepthOfNaryTree().maxDepthRecursive(node1);
        System.out.println(maxDepth);

    }
}
