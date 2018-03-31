package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sshil on 5/12/2016.
 */
public class DiagonalTraversalOfBinaryTree {

    public void print(BinaryTreeNode root){
        Queue<BinaryTreeNode> list = new LinkedList<>();
        BinaryTreeNode temp = root;
        while (temp != null){
            list.add(temp);
            System.out.print(temp.getData()+"->");
            temp = temp.getRight();
        }
        System.out.println();

        while (list.size() > 0){
            int size = list.size();
            for ( int i = 0 ; i < size ; i++) {
                BinaryTreeNode node = list.poll();
                printAndInsertInQueue(node, list);
            }
            System.out.println();
        }
    }

    private void printAndInsertInQueue(BinaryTreeNode node, Queue<BinaryTreeNode> list) {
        BinaryTreeNode temp = node.getLeft();
        while(temp != null){
            System.out.print(temp.getData()+"->");
            list.add(temp);
            temp = temp.getRight();
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(8);
        root.setLeft(new BinaryTreeNode(3));
        root.setRight(new BinaryTreeNode(10));

        root.getLeft().setLeft(new BinaryTreeNode(1));
        root.getLeft().setRight(new BinaryTreeNode(6));
        root.getLeft().getRight().setLeft(new BinaryTreeNode(4));
        root.getLeft().getRight().setRight(new BinaryTreeNode(7));

        root.getRight().setRight(new BinaryTreeNode(14));
        root.getRight().getRight().setLeft(new BinaryTreeNode(13));
        new DiagonalTraversalOfBinaryTree().print(root);
//        String str = "c";
//        int t = str.charAt(0);
//        System.out.println(t);
    }
}
