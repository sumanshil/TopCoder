package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/zigzag-tree-traversal/
public class ZigZagTreeTraversal {

    public void zigZag(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean printLeftToRight = false;
        while (!queue.isEmpty()) {
            if (printLeftToRight){
                printLeftToRight(queue);
            } else {
                printRightToLeft(queue);
            }
            int size = queue.size();
            for (int i = 0 ; i < size ; i++){
                BinaryTreeNode node = queue.remove();
                if (node.getLeft() != null){
                    queue.add(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            printLeftToRight = !printLeftToRight;
        }
    }

    private void printRightToLeft(Queue<BinaryTreeNode> queue) {
        BinaryTreeNode[] array = queue.toArray(new BinaryTreeNode[0]);
        for ( int i = array.length-1 ; i>= 0 ; i--) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    private void printLeftToRight(Queue<BinaryTreeNode> queue) {
        BinaryTreeNode[] array = queue.toArray(new BinaryTreeNode[0]);
        for ( int i = 0 ; i< array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);

        root.getLeft().insertInLeft(7);
        root.getLeft().insertInRight(6);

        root.getRight().insertInLeft(5);
        root.getRight().insertInRight(4);
        new ZigZagTreeTraversal().zigZag(root);
    }

}
