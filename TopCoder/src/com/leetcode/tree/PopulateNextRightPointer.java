package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulateNextRightPointer {
    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    public void connect1(TreeLinkNode root){
        TreeLinkNode level_start = root;
        while (level_start != null) {
            TreeLinkNode current = level_start;
            while (current != null) {

                if (current.left != null) {
                    current.left.next = current.right;
                }

                if (current.right != null && current.next != null) {
                    current.right.next = current.next.left;
                }
                current = current.next;
            }
            level_start = level_start.left;
        }
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
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);

        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);

        root.right.left = new TreeLinkNode(6);
        root.right.right = new TreeLinkNode(7);
        new PopulateNextRightPointer().connect1(root);

    }
}
