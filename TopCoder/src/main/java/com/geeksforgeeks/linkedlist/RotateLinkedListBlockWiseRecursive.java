package com.geeksforgeeks.linkedlist;

public class RotateLinkedListBlockWiseRecursive {


    public static void main(String[] args) {
        LinkedListNode start = new LinkedListNode(1);
        start.next = new LinkedListNode(2);
        start.next.next = new LinkedListNode(3);
        start.next.next.next = new LinkedListNode(4);
        start.next.next.next.next = new LinkedListNode(5);
        start.next.next.next.next.next = new LinkedListNode(6);
        start.next.next.next.next.next.next = new LinkedListNode(7);
        start.next.next.next.next.next.next.next = new LinkedListNode(8);
        start.next.next.next.next.next.next.next.next = new LinkedListNode(9);
        new RotateLinkedListBlockWiseRecursive().rotate(start,3,2,9);

    }

    private void rotate(LinkedListNode start, int blockSize, int rotationSize, int totalLength) {
        LinkedListNode newNode = rotateRecursive(start, blockSize, rotationSize, totalLength);
        while(newNode != null){
            System.out.println(newNode.data);
            newNode = newNode.next;
        }
    }

    private LinkedListNode rotateRecursive(LinkedListNode start, int blockSize, int rotationSize, int totalLength) {
        if (start == null) {
            return null;
        }

        LinkedListNode firstNodeOfNextBlock = getNode(start, blockSize);
        LinkedListNode beginNode = rotateBlock(start, blockSize, rotationSize);
        LinkedListNode tempNode = beginNode;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        LinkedListNode nNode = rotateRecursive(firstNodeOfNextBlock, blockSize, rotationSize, totalLength);
        tempNode.next = nNode;
        return beginNode;
    }

    private LinkedListNode rotateBlock(LinkedListNode start, int blockSize, int rotationSize) {
        LinkedListNode endNode = getNode(start, blockSize-1);
        for ( int i = 0 ; i < rotationSize ; i++ ) {

            LinkedListNode temp = start;
            while (temp.next != endNode) {
                temp = temp.next;
            }

            endNode.next = start;
            temp.next = null;
            start = endNode;
            endNode = temp;
        }
        return start;
    }


    private LinkedListNode getNode(LinkedListNode start, int blockSize) {
        if (start == null) {
            return null;
        }
        int count = 0;
        LinkedListNode temp = start;
        while (count <= blockSize){
            if (temp == null) {
                return null;
            }
            temp = temp.next;
            count++;
        }
        return temp;
    }
}
