package com.leetcode.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/
public class NaryTreePostOrderTraversal {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public List<Integer> postorder(Node root) {
        List<Integer> result = new LinkedList<>();
        recursive(root, result);
        return result;
    }

    private void recursive(Node root, List<Integer> result) {
        if (root == null || root.children.size() == 0) {
            result.add(root.val);
            return;
        }

        for (Node child : root.children) {
            recursive(child, result);
        }
        result.add(root.val);
    }


    public static void main(String[] args) {
        Node node5 = new Node(5, Collections.emptyList());
        Node node6 = new Node(6, Collections.emptyList());

        Node node3 = new Node(3, Arrays.asList(node5, node6));
        Node node2 = new Node(2, Collections.emptyList());
        Node node4 = new Node(4, Collections.emptyList());

        Node node1 = new Node(1, Arrays.asList(node3, node2, node4));

        List<Integer> result = new NaryTreePostOrderTraversal().postorder(node1);

        result.stream().forEach(System.out::println);

    }

}
