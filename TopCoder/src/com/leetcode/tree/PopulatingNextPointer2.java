package com.leetcode.tree;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/description/

public class PopulatingNextPointer2 {

    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeLinkNode{" +
                   "val=" + val +
                   '}';
        }
    }

    public void connect (TreeLinkNode root) {
        if (root == null) {
            return;
        }
        iterative(root);
//        System.out.println(root);
    }

    private void iterative(TreeLinkNode root) {
        TreeLinkNode current = root;
        while (current != null) {
            if (current.left != null) {
                link(current, current.left);
                current = current.left;
            } else if (current.right != null) {
                link(current, current.right);
                current = current.right;
            } else {
                current = current.next;
            }
        }
    }

    private void link(TreeLinkNode parent, TreeLinkNode child) {
        TreeLinkNode currentParent = parent;
        TreeLinkNode currentChild = child;

        while (currentParent != null) {
            if (currentParent.left != null && currentParent.left != currentChild) {
                currentChild.next = currentParent.left;
                currentChild = currentParent.left;
            } else if (currentParent.right != null && currentParent.right != currentChild) {
                currentChild.next = currentParent.right;
                currentChild = currentParent.right;
                if (currentParent.left != null) {
                    currentParent = currentParent.next;
                }
            } else {
                currentParent = currentParent.next;
            }
        }
    }

    private TreeLinkNode getNextAvailable(TreeLinkNode next) {
        while (next != null) {
            if (next.left != null) {
                return next.left;
            }
            if (next.right != null) {
                return next.right;
            }
            next = next.next;
        }
        return null;
    }

    public static void main(String[] args) {
        /*
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);

        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);

        root.right.right = new TreeLinkNode(7);
        new PopulatingNextPointer2().connect(root);
        */
        TreeLinkNode root = new TreeLinkNode(2);
        root.left = new TreeLinkNode(1);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(0);
        root.left.right = new TreeLinkNode(7);

        root.right.left = new TreeLinkNode(9);
        root.right.right = new TreeLinkNode(1);

        root.left.left.left = new TreeLinkNode(2);
        root.left.right.left = new TreeLinkNode(1);
        root.left.right.right = new TreeLinkNode(0);

        root.right.right.right = new TreeLinkNode(8);
        root.right.right.left = new TreeLinkNode(8);

        root.left.right.right.left = new TreeLinkNode(7);
        new PopulatingNextPointer2().connect(root);
    }

//              2
//         1             3
//      0     7       9     1
//    2  #  1   0   #  #  8   8
//   # #   # # 7
}
