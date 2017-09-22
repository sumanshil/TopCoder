package com.geeksforgeeks.linkedlist;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/rotate-linked-list-block-wise/
public class RotateLinkedListBlockWise {

    public void rotate(LinkedListNode start, int blockSize, int rotateSize, int length) {
        LinkedListNode begin;
        LinkedListNode end;
        LinkedListNode firstNodeOfResultList = null;
        for ( int i = 0 ; i <= length - blockSize ;i = i + blockSize ){
            begin = getNode(i, start);
            end = getNode(i + blockSize - 1, start);
            LinkedListNode firstNodeOfNextBlock = getNode( i + blockSize, start);
            LinkedListNode lastNodeOfPreviousBlock = null;
            if ( i > 0) {
                lastNodeOfPreviousBlock = getNode( i-1, start);
            }
            List<LinkedListNode> list = rotateBlock(begin, end, rotateSize, blockSize);
            if ( i == 0) {
                start = list.get(0);
            }
            list.get(1).next = firstNodeOfNextBlock;
            if (lastNodeOfPreviousBlock != null){
                lastNodeOfPreviousBlock.next = list.get(0);
            }
        }

        LinkedListNode temp = start;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }

    }

    private List<LinkedListNode> rotateBlock(LinkedListNode begin, LinkedListNode end, int rotateSize, int blockSize) {
        LinkedListNode firstNode = begin;
        LinkedListNode lastNode = end;
        lastNode.next = null;
        for ( int i = 0 ; i < rotateSize ; i++) {
            LinkedListNode temp = firstNode;
            while (temp.next != lastNode) {
                temp = temp.next;
            }
            lastNode.next = firstNode;
            temp.next = null;
            firstNode = lastNode;
            lastNode = temp;
        }
        List<LinkedListNode> listNodes = new ArrayList<>();
        listNodes.add(firstNode);
        listNodes.add(lastNode);
        return listNodes;
    }

    private LinkedListNode getNode(int index, LinkedListNode start) {
        int count = 0;
        LinkedListNode temp = start;
        while (temp != null && count < index){
            temp = temp.next;
            count++;
        }
        return temp;
    }


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
        new RotateLinkedListBlockWise().rotate(start,3,2,9);
    }
}
