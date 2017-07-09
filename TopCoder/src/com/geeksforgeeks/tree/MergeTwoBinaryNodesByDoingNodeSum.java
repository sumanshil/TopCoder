package com.geeksforgeeks.tree;

/**
 * Created by sshil on 6/23/17.
 */
//http://www.geeksforgeeks.org/merge-two-binary-trees-node-sum/
public class MergeTwoBinaryNodesByDoingNodeSum {

    public void find(BinaryTreeNode root1, BinaryTreeNode root2){

        BinaryTreeNode root3 = sumRecursive(root1, root2);
        inOrder(root3);
    }

    private void inOrder(BinaryTreeNode root3) {
        if (root3 != null){
            inOrder(root3.getLeft());
            System.out.println(root3.getData());
            inOrder(root3.getRight());
        }
    }

    private BinaryTreeNode sumRecursive(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }

        int nodeValue1 = root1 == null ? 0 : root1.getData();
        int nodeValue2 = root2 == null ? 0 : root2.getData();
        BinaryTreeNode newRoot = new BinaryTreeNode(nodeValue1+nodeValue2);
        newRoot.setLeft(sumRecursive(root1 != null ? root1.getLeft() : null,
                root2 != null ? root2.getLeft() : null));
        newRoot.setRight(sumRecursive(root1 != null ? root1.getRight() :null,
                root2 != null ? root2.getRight() : null));
        return newRoot;
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.insertInLeft(1);
        root.insertInRight(4);
        root.getLeft().insertInLeft(5);

        BinaryTreeNode root1 = new BinaryTreeNode(3);
        root1.insertInLeft(6);
        root1.insertInRight(1);
        root1.getLeft().insertInRight(2);
        root1.getRight().insertInRight(7);
        new MergeTwoBinaryNodesByDoingNodeSum().find(root, root1);
    }
}
