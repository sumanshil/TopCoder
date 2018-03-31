package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * Created by sshil on 7/16/17.
 */
//http://www.geeksforgeeks.org/iterative-method-to-find-ancestors-of-a-given-binary-tree/
public class PostOrderIterativeTraversal {

    public void find(BinaryTreeNode root, BinaryTreeNode targetNode){
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> competedNodes = new ArrayList<>();
        while (!stack.isEmpty()){
            BinaryTreeNode node = stack.peek();
            BinaryTreeNode leftNode = node.getLeft();
            while(leftNode != null){
                stack.push(leftNode);
                leftNode = leftNode.getLeft();
            }

            BinaryTreeNode currentPeek = stack.peek();
            if (!competedNodes.contains(currentPeek.getData()) && currentPeek.getRight() != null){
                stack.push(currentPeek.getRight());
                competedNodes.add(currentPeek.getData());
            } else {
                BinaryTreeNode topOfStackNode = stack.pop();
                if (topOfStackNode == targetNode){
                    printStackContent(stack);
                    break;
                }
                while (stack.peek().getRight() == null
                        || competedNodes.contains(stack.peek().getData())){
                     stack.pop();
                }
                if (stack.peek().getRight() != null){
                    competedNodes.add(stack.peek().getData());
                    stack.push(stack.peek().getRight());
                }
            }
        }
    }


    public void find1(BinaryTreeNode root, int targetData) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BinaryTreeNode peek = stack.peek();
            BinaryTreeNode leftNode =peek.getLeft();
            while (leftNode != null){
                stack.push(leftNode);
                leftNode = leftNode.getLeft();
            }

            BinaryTreeNode topNode = null;
            if (stack.peek().getData() == targetData){
                printStackContent(stack);
                break;
            }
            while (!stack.isEmpty() && stack.peek().getRight() == null){
                topNode = stack.pop();
            }
            while (!stack.isEmpty() &&
                    topNode != null &&
                    stack.peek().getRight() == topNode) {
                topNode = stack.pop();
            }
            if (!stack.isEmpty()  && stack.peek().getRight() != null){
                stack.push(stack.peek().getRight());
            }
        }
    }

    private void printStackContent(Stack<BinaryTreeNode> stack) {
        while (!stack.isEmpty()){
            BinaryTreeNode node = stack.pop();
            System.out.println(node.getData());
            System.out.println("=======");
        }

    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(7);
        root.getLeft().insertInLeft(3);
        root.getLeft().insertInRight(5);
        root.getLeft().getLeft().insertInLeft(4);
        root.getLeft().getRight().insertInRight(6);

        root.getRight().insertInLeft(8);
        root.getRight().insertInRight(9);
        root.getRight().getRight().insertInLeft(10);
        new PostOrderIterativeTraversal().find1(root, root.getLeft().getRight().getRight().getData());
    }
}
