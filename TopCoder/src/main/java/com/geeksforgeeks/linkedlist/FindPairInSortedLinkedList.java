package com.geeksforgeeks.linkedlist;

/**
 * Created by sshil on 1/3/17.
 */
//http://www.geeksforgeeks.org/find-pair-given-sum-sorted-singly-linked-without-extra-space/
public class FindPairInSortedLinkedList {
    static class Node {
        private int data;
        private Node next;

        public Node (int data){
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public void find (Node root, int targetNumber) {
        int count = findCount(root);

        int mid ;
        if (count % 2 == 0){
            mid = count / 2;
        } else {
            mid = (count / 2);
        }

        Node midNode = root;
        for ( int i = 0 ; i < mid ; i++){
            midNode = midNode.getNext();
        }

        Node end = reverse(midNode);
        System.out.println(end.data);

        while (root != null && end != null && root != end) {
            int total = root.data + end.data;
            if (total < targetNumber) {
                root = root.getNext();
            } else if (total > targetNumber) {
                end = end.getNext();
            } else {
                System.out.println("Found "+ root.data + " : "+ end.data);
                root = root.getNext();
                end = end.getNext();
            }
        }

    }

    private Node reverse(Node node) {
        if (node.getNext() == null) {
            return node;
        }

        Node current = node;
        Node next = current.next;

        Node last = reverse(next);
        current.setNext(null);
        next.setNext(current);
        return last;
    }

    private int findCount(Node root) {
        Node temp = root;
        int count = 0;
        while (temp != null){
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    public static void main(String[] args) {

        Node node = new Node(3);
        node.setNext(new Node(6));
        node.getNext().setNext(new Node(7));
        node.getNext().getNext().setNext(new Node(8));
        node.getNext().getNext().getNext().setNext(new Node(9));
        node.getNext().getNext().getNext().getNext().setNext(new Node(10));
        node.getNext().getNext().getNext().getNext().getNext().setNext(new Node(11));

        new FindPairInSortedLinkedList().find(node, 17);
    }
}
