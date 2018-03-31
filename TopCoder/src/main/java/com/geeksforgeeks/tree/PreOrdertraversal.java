package com.geeksforgeeks.tree;

import java.util.Stack;

/**
 * Created by sshil on 8/4/2016.
 */
public class PreOrdertraversal {

    public void printPreOrderRecursive(BinaryTreeNode root){
        recursive(root);
    }

    private void recursive(BinaryTreeNode root) {
        if (root != null){
            System.out.println(root.getData());
            recursive(root.getLeft());
            recursive(root.getRight());
        }
    }


    public void nonRecursive(BinaryTreeNode root){
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode p = root;
        while (!stack.empty()){
            while( p != null){
                System.out.println(p.getData());
                if ( p.getLeft() != null){
                    stack.push(p.getLeft());
                }
                p = p.getLeft();
            }
            BinaryTreeNode p1 = stack.pop();
            if (p1.getRight() != null){
                stack.push(p1.getRight());
            }
            p = p1.getRight();
        }

    }

    public void inOrderRecursive(BinaryTreeNode root){
        if (root != null){
            inOrderRecursive(root.getLeft());
            System.out.println(root.getData());
            inOrderRecursive(root.getRight());
        }
    }

    public void inOrderNonRecursive(BinaryTreeNode root){
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode p = root;
        while(!stack.empty()) {
            while ( p != null ) {
                if ( p.getLeft() != null){
                    stack.push(p.getLeft());
                }
                p = p.getLeft();
            }
            BinaryTreeNode p1 = stack.pop();
            System.out.println(p1.getData());
            p = p1.getRight();
            if ( p != null){
                stack.push(p);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(7);
        root.insertInLeft(6);
        root.insertInRight(8);
        root.getLeft().insertInLeft(5);
        root.getLeft().insertInRight(4);

        root.getRight().insertInLeft(9);
        root.getRight().insertInRight(10);
        //new PreOrdertraversal().nonRecursive(root);
        new PreOrdertraversal().inOrderNonRecursive(root);
    }
}
