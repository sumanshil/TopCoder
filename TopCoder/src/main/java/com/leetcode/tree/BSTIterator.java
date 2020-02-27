package com.leetcode.tree;

import java.util.Stack;

//https://leetcode.com/problems/binary-search-tree-iterator/
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode current;

    public BSTIterator(TreeNode root) {
        current = root;
    }

    /** @return the next smallest number */
    public int next() {
        int temp = 0;
        if (current != null || !stack.isEmpty()) {

            while (current!= null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode peekNode = stack.pop();
            temp = peekNode.val;
            current = peekNode.right;
        }
        return temp;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return current != null || !stack.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);

        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }
}
