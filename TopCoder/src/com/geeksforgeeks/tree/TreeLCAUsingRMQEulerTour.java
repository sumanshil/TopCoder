package com.geeksforgeeks.tree;


import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TreeLCAUsingRMQEulerTour {

    public static List<Integer> eulerTour = new LinkedList<>();
    public static List<Integer> eulerTourLevel = new LinkedList<>();
    public static Set<FirstOccurenceNode> eulerTourFirstOccurence = new LinkedHashSet<>();

    static class FirstOccurenceNode {
        int data;
        int index;
        public FirstOccurenceNode(int data, int index) {
            this.data = data;
            this.index = index;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof FirstOccurenceNode)) {
                return false;
            }

            FirstOccurenceNode that = (FirstOccurenceNode) o;

            return getData() == that.getData();
        }

        @Override
        public int hashCode() {
            return getData();
        }
    }
    public static Integer index = 0;
    public void find (BinaryTreeNode root, int data1, int data2) {
        recursive(root, 0);
        printData();

        final AtomicInteger index1 = new AtomicInteger(0);
        final AtomicInteger index2 = new AtomicInteger(0);

        eulerTourFirstOccurence.stream().forEach(e -> {
            if (e.data == data1) {
                index1.set(e.index);
            } else if ( e.data == data2) {
                index2.set(e.index);
            }
        });

        int lower = index1.intValue() < index2.intValue() ? index1.intValue() : index2.intValue();
        int higher = index1.intValue() < index2.intValue() ? index2.intValue() : index1.intValue();

        int minLevel = Integer.MAX_VALUE;
        AtomicInteger minIndex = new AtomicInteger(0);
        for ( int i = lower+1 ; i < higher ; i++){
            if (eulerTourLevel.get(i) < minLevel) {
                minLevel = eulerTourLevel.get(i);
                minIndex.set(i);
            }
        }

        int r = IntStream.range(0, eulerTourLevel.size()).filter( index -> index == minIndex.intValue()).findFirst().getAsInt();
        System.out.println(eulerTour.get(r));


    }

    private void printData() {
        System.out.println("Tour List");
        eulerTour.stream().map(e -> e +"->").forEach(System.out::print);
        System.out.println();

        System.out.println("Tour Level List");
        eulerTourLevel.stream().map(e -> e +"->").forEach(System.out::print);
        System.out.println();

        System.out.println("First occurence List");
        eulerTourFirstOccurence.stream().map(e -> e.data+":"+e.index +"->").forEach(System.out::print);
        System.out.println();

    }

    private void recursive(BinaryTreeNode root, int level) {
        if (root == null) {
            return;
        }

        populate(root, level);

        recursive(root.getLeft(), level+1);
        if (!isLeafNode(root)) {
            populate(root, level);
        }

        recursive(root.getRight(), level+1);

        if (!isLeafNode(root)) {
            populate(root, level);
        }

    }

    private boolean isLeafNode(BinaryTreeNode root) {
        return root.getLeft() == null && root.getRight() == null;
    }

    private void populate(BinaryTreeNode root, int level) {
        FirstOccurenceNode firstOccurenceNode1 = new FirstOccurenceNode(root.getData(), index++);
        if (!eulerTourFirstOccurence.contains(firstOccurenceNode1)) {
            eulerTourFirstOccurence.add(firstOccurenceNode1);
        }
        eulerTour.add(root.getData());
        eulerTourLevel.add(level);
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);
        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);
        root.getLeft().getRight().insertInLeft(8);
        root.getLeft().getRight().insertInRight(9);

        root.getRight().insertInLeft(6);
        root.getRight().insertInRight(7);
        new TreeLCAUsingRMQEulerTour().find(root, 4, 9);
    }

}
