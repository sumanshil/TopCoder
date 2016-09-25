package com.geeksforgeeks.tree;

/**
 * Created by sshil on 9/18/16.
 */
//http://www.geeksforgeeks.org/in-place-convert-bst-into-a-min-heap/
public class ConvertBSTToMinHeap {

    private BinaryTreeNode head = null;
    private BinaryTreeNode tail = null;

    public void build(BinaryTreeNode root) {
        inOrder(root);
        current = head;
        BinaryTreeNode heapRoot = buildMinHeap(head);
        printInOrder(heapRoot);
    }

    public void printInOrder(BinaryTreeNode root){
        if (root != null){
            printInOrder(root.getLeft());
            System.out.println(root.getData());
            printInOrder(root.getRight());
        }
    }

    private BinaryTreeNode current = null;
    private boolean rewinding = false;
    private BinaryTreeNode buildMinHeap(BinaryTreeNode root) {
        if (root == null) {
            rewinding = true;
            return null;
        }
        BinaryTreeNode nextNode = current == null ? null : current.getRight();
        BinaryTreeNode nextNextNode = nextNode == null ? null : nextNode.getRight();
        if (!rewinding) {
            if (nextNextNode != null && nextNextNode.getRight() != null && nextNextNode.getRight().getRight() != null) {
                current = nextNextNode.getRight().getRight();
            } else {
                current = null;
            }
        }
        root.setLeft(buildMinHeap(nextNode));
        root.setRight(buildMinHeap(nextNextNode));

        return root;
    }

    private void inOrder(BinaryTreeNode root) {
        if (root != null) {
            inOrder(root.getLeft());
            if (head == null) {
                head = root;
                tail = root;
            } else {
                tail.setRight(root);
                root.setLeft(tail);
                tail = root;
            }
            inOrder(root.getRight());
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(8);
        root.insertInLeft(4);
        root.insertInRight(12);
        root.getLeft().insertInLeft(2);
        root.getLeft().insertInRight(6);
        root.insertInRight(12);
        root.getRight().insertInLeft(10);
        root.getRight().insertInRight(14);
        new ConvertBSTToMinHeap().build(root);
    }
}
