package com.geeksforgeeks.linkedlist;

public class ReverseFirstKElementsInLinkedList {

    public void reverse(LinkedListNode start, int k) {
        LinkedListNode current = start;
        for (int i = 0 ; i < k ; i++) {
            current = current.next;
        }
        LinkedListNode currentNode = start;
        LinkedListNode reversedStart = reverseRecursive(currentNode, 0, k-1);
        start.next = current;
        LinkedListNode c1 = reversedStart;
        while ( c1 != null) {
            System.out.println(c1.data);
            c1 = c1.next;
        }
    }

    private LinkedListNode reverseRecursive(LinkedListNode currentNode,
                                            int currentCount,
                                            int maxCount) {
        LinkedListNode nextNode = currentNode.next;

        if (currentCount == maxCount) {
            return null;
        }

        LinkedListNode n1 = reverseRecursive(nextNode, currentCount + 1, maxCount);

        if (nextNode != null) {
            nextNode.next = currentNode;
        }
        if (n1 == null && nextNode != null) {
            return nextNode;
        }
        if (n1 != null){
            return n1;
        }
        return null;
    }


    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode(1);
        node.next = new LinkedListNode(2);
        node.next.next = new LinkedListNode(3);
        node.next.next.next = new LinkedListNode(4);
        node.next.next.next.next = new LinkedListNode(5);
        node.next.next.next.next.next = new LinkedListNode(6);
        node.next.next.next.next.next.next = new LinkedListNode(7);
        node.next.next.next.next.next.next.next = new LinkedListNode(8);
        node.next.next.next.next.next.next.next.next = new LinkedListNode(9);
        node.next.next.next.next.next.next.next.next.next = new LinkedListNode(10);

        new ReverseFirstKElementsInLinkedList().reverse(node, 5);
    }

}
