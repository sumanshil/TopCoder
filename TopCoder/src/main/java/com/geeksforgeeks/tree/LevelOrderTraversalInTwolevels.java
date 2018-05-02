package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//https://www.geeksforgeeks.org/level-order-traversal-direction-change-every-two-levels/
public class LevelOrderTraversalInTwolevels {

    public void find (BinaryTreeNode root) {
        List<BinaryTreeNode> parent = new LinkedList<>();
        List<BinaryTreeNode> children = new LinkedList<>();
        parent.add(root);
        int level = 0;
        boolean leftToRight = false;

        while (!parent.isEmpty()) {
            for (int i = 0 ; i < parent.size() ; i++) {
                BinaryTreeNode node = parent.get(i);
                if (node.getLeft() != null) {
                    children.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    children.add(node.getRight());
                }
            }
            if (level % 2 == 0) {
                if (!leftToRight) {
                    leftToRight = true;
                } else {
                    leftToRight = false;
                }
            }

            if (leftToRight) {
                String str = parent.stream().map(e -> e.getData()+"").collect(Collectors.joining("->"));
                System.out.println(str);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = parent.size()-1 ; i >= 0 ; i--) {
                    stringBuilder.append(parent.get(i)).append("->");
                }
                System.out.println(stringBuilder.toString());
            }
            parent = children;
            children = new ArrayList<>();
            level++;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);

        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);

        root.getRight().insertInLeft(6);
        root.getRight().insertInRight(7);

        root.getLeft().getLeft().insertInLeft(8);
        root.getLeft().getLeft().insertInRight(9);

        root.getLeft().getRight().insertInLeft(3);
        root.getLeft().getRight().insertInRight(1);

        root.getRight().getLeft().insertInLeft(4);
        root.getRight().getLeft().insertInRight(2);

        root.getRight().getRight().insertInLeft(7);
        root.getRight().getRight().insertInRight(2);

        root.getLeft().getLeft().getRight().insertInLeft(8);

        root.getLeft().getRight().getRight().insertInLeft(17);
        root.getLeft().getRight().getRight().insertInRight(18);

        root.getRight().getLeft().getRight().insertInRight(19);
        new LevelOrderTraversalInTwolevels().find(root);
    }
}
