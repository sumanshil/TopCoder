package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
public class LevelOrderTraversalInSpiralForm {

    public void find (BinaryTreeNode root){
        printSpiralForm(root);
    }

    private void printSpiralForm(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = false;
        while (!queue.isEmpty()){
            BinaryTreeNode[] arr = queue.toArray(new BinaryTreeNode[0]);
            if (!leftToRight) {
                for (int i = arr.length - 1; i >= 0; i--) {
                    System.out.println(arr[i]);
                }
            } else {
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
                }
            }
            int queueSize = queue.size();
            for (int i = 0; i < queueSize ; i++ ) {
                BinaryTreeNode current = queue.poll();
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }
            leftToRight = !leftToRight;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);

        root.getLeft().insertInLeft(7);
        root.getLeft().insertInRight(6);

        root.getRight().insertInLeft(5);
        root.getRight().insertInRight(4);
        new LevelOrderTraversalInSpiralForm().printSpiralForm(root);

    }
}
