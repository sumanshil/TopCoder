package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//http://www.geeksforgeeks.org/construct-complete-binary-tree-given-array/
public class CreateBinaryTreeFromLevelOrderTraversal {

    public BinaryTreeNode create(int[] arr) {
        List<BinaryTreeNode> nodeList = new LinkedList<>();
        BinaryTreeNode root = new BinaryTreeNode(arr[0]);
        nodeList.add(root);
        root.setLeft(new BinaryTreeNode(arr[1]));
        root.setRight(new BinaryTreeNode(arr[2]));
        nodeList.add(root.getLeft());
        nodeList.add(root.getRight());

        int index = 3;
        int nodeIndex = 1;
        while (index < arr.length){
            BinaryTreeNode node = nodeList.get(nodeIndex++);

            BinaryTreeNode newLeftNode = new BinaryTreeNode(arr[index]);
            node.setLeft(newLeftNode);
            nodeList.add(newLeftNode);

            if (index + 1 < arr.length) {
                BinaryTreeNode newRightNode = new BinaryTreeNode(arr[index + 1]);
                node.setRight(newRightNode);
                nodeList.add(newRightNode);
            }
            index = index + 2;
        }
        return root;
    }

    public void printLevelOrder(BinaryTreeNode root){
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            BinaryTreeNode current = queue.poll();
            System.out.println(current);
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    public void createRecursion(int[] arr){
        BinaryTreeNode root = recursiveUtil(arr, 0);
        printLevelOrder(root);
    }

    private BinaryTreeNode recursiveUtil(int[] arr, int index) {
        if (index >= arr.length) {
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(arr[index]);
        root.setLeft(recursiveUtil(arr, (2*index + 1)));
        root.setRight(recursiveUtil(arr, (2*index + 2)));
        return root;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        new CreateBinaryTreeFromLevelOrderTraversal().createRecursion(arr);
    }
}
