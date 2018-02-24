package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRightPointer {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode previous = null;
            for ( int i = 0 ; i < size ; i++) {
                TreeLinkNode currentNode = queue.remove();
                if (previous != null) {
                    previous.next = currentNode;
                }
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
                previous = currentNode;
            }
        }
    }

    public static void main(String[] args) {

    }
}
