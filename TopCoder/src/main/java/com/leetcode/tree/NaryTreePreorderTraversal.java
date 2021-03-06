package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

public class NaryTreePreorderTraversal {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val,List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Solution {
        private List<Integer> result = new LinkedList<>();
        public List<Integer> preorder(Node root) {
            if (root == null) {
                return result;
            }

            result.add(root.val);

            for (Node node : root.children) {
                preorder(node);
            }
            return result;
        }
    }

    public static void main(String[] args) {

    }

}
