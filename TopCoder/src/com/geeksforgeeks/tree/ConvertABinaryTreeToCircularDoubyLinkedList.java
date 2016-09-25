package com.geeksforgeeks.tree;

/**
 * Created by sshil on 9/7/16.
 */
public class ConvertABinaryTreeToCircularDoubyLinkedList {

    private BinaryTreeNode head = null;
    private BinaryTreeNode tail = null;
    private BinaryTreeNode prevNode = null;

    public void find(BinaryTreeNode root) {
        inOrder(root);
        if (head != null && tail != null){
            head.setLeft(tail);
            tail.setRight(head);
        }

    }

    public void inOrder(BinaryTreeNode root) {
        if (root != null) {
            inOrder(root.getLeft());
            if (head == null){
                head = root;
            }
            tail = root;
            if (prevNode != null){
                prevNode.setRight(root);
                root.setLeft(prevNode);
            }
            prevNode = root;

            inOrder(root.getRight());
        }
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.insertInLeft(12);
        root.insertInRight(15);
        root.getLeft().insertInLeft(25);
        root.getLeft().insertInRight(30);
        root.insertInRight(15);
        root.getRight().insertInLeft(36);
        new ConvertABinaryTreeToCircularDoubyLinkedList().find(root);

    }
}
