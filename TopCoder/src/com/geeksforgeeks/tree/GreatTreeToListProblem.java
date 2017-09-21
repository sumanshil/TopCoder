package com.geeksforgeeks.tree;

import java.time.Duration;
import java.time.Instant;

public class GreatTreeToListProblem {

    public void transform(BinaryTreeNode root){
        
        LinkedList start = convert(root);
        
        
    }

    class  LinkedList {
        BinaryTreeNode start;
        BinaryTreeNode end;
    }

    private LinkedList convert(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }

        LinkedList leftList = convert(root.getLeft());
        LinkedList rightList = convert(root.getRight());

        LinkedList currentList;
        if (leftList != null) {
            currentList = leftList;
            currentList.end.setRight(root);
            root.setLeft(currentList.end);
        } else {
            currentList = new LinkedList();
            currentList.start = root;
            currentList.end = currentList.start;
        }

        if (rightList != null) {
            root.setRight(rightList.start);
            rightList.start.setLeft(root);
            currentList.end = rightList.end;
        }
        return currentList;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.setLeft(new BinaryTreeNode(2));
        root.setRight(new BinaryTreeNode(3));

        root.getLeft().setLeft(new BinaryTreeNode(4));
        root.getLeft().setRight(new BinaryTreeNode(5));

        root.getRight().setLeft(new BinaryTreeNode(6));
        root.getRight().setRight(new BinaryTreeNode(7));
        LinkedList linkedList = new GreatTreeToListProblem().convert(root);
        BinaryTreeNode current = linkedList.start;
        while (current != null){
            System.out.println(current.getData());
            current = current.getRight();
        }
    }
}
