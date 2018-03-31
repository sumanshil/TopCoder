package com.geeksforgeeks.recursion;

import com.geeksforgeeks.linkedlist.LinkedListNode;

public class CheckIfListIsAscending {

    public boolean isAscending(LinkedListNode start){
        boolean result = isAscendingRecursive(start, null);
        return result;
    }
    private boolean isAscendingRecursive(LinkedListNode start, LinkedListNode prev) {
        if(start == null)
            return true;
        boolean result = true;
        if (prev != null){
            result =  (prev.data > start.data) ? false : true;
        }
        prev = start;    
        return result && isAscendingRecursive(start.next, prev);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        LinkedListNode start = new LinkedListNode(1);
        start.next = new LinkedListNode(2);
        start.next.next = new LinkedListNode(3);
        start.next.next.next = new LinkedListNode(5);
        start.next.next.next.next = new LinkedListNode(4);
        start.next.next.next.next.next = new LinkedListNode(6);
        boolean result = new CheckIfListIsAscending().isAscending(start);
        System.out.println(result);

    }

}
