package com.geeksforgeeks.linkedlist;

public class ReverseNodesInKGroup {


    public LinkedListNode reverse(LinkedListNode start, int k) {
        if (start == null){
            return null;
        }

        LinkedListNode startNode = start;
        LinkedListNode currentNode = start;
        int count = k;
        while (count-- > 0 && startNode != null) {
            currentNode = startNode;
            startNode = startNode.next;
        }
        if ( count > 0 || startNode == null) {
            return start;
        } else {
            reverse(null, start, currentNode);
        }
        LinkedListNode last = currentNode;
        while (last.next != null){
            last = last.next;
        }
        last.next = reverse(startNode, k);
        return currentNode;
    }


    private void reverse(LinkedListNode prev, LinkedListNode current, LinkedListNode end) {
        if (current == end){
            current.next= prev;
            return;
        }
        reverse(current, current.next, end);
        current.next = prev;
    }

    public static void main(String[] args) {
        LinkedListNode startNode = new LinkedListNode(1);
        startNode.next = new LinkedListNode(2);
        startNode.next.next = new LinkedListNode(3);
        startNode.next.next.next = new LinkedListNode(4);
        startNode.next.next.next.next = new LinkedListNode(5);

        int k = 2;
        LinkedListNode node = new ReverseNodesInKGroup().reverse(startNode, k);
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
            System.out.println();
        }
    }
}
