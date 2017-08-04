package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/print-nodes-k-distance-root-iterative/
public class PrintNodesAtKDistanceFromRoot {

    public void find(BinaryTreeNode root, int k){
        findRecursive(root, k , 1);
    }

    private void findRecursive(BinaryTreeNode root, int k, int currentHeight) {
        if (root == null) {
            return;
        }
        if (currentHeight == k){
            System.out.println(root.getData());
            return;
        }
        findRecursive(root.getLeft(), k , currentHeight+1);
        findRecursive(root.getRight(), k, currentHeight + 1);
    }

    public void findIterative(BinaryTreeNode root, int k){
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;

        while (!queue.isEmpty()) {
            if (count == k){
                printQueueContent(queue);
                break;
            }
            int queueSize = queue.size();
            for ( int i = 0 ; i < queueSize ; i++){
                BinaryTreeNode node = queue.poll();
                if (node.getLeft() != null){
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null){
                    queue.add(node.getRight());
                }
            }
            count++;
        }

    }

    private void printQueueContent(Queue<BinaryTreeNode> queue) {
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(20);
        root.insertInLeft(10);
        root.insertInRight(30);

        root.getLeft().insertInLeft(5);
        root.getLeft().insertInRight(15);
        root.getRight().insertInLeft(25);
        root.getRight().insertInRight(40);

        root.getLeft().getRight().insertInLeft(12);
        new PrintNodesAtKDistanceFromRoot().findIterative(root, 3);
    }
}
