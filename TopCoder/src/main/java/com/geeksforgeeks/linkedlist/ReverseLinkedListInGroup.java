package com.geeksforgeeks.linkedlist;

public class ReverseLinkedListInGroup {

	public static LinkedListNode reverse(LinkedListNode start, int k){
         LinkedListNode current = start;
         LinkedListNode prev = null;
         LinkedListNode next = current.next;
         int count = 1;
         while(next != null  && count < k){
        	 current.next = prev;
        	 prev = current;
        	 current = next;
        	 next= current.next;
             count++;        	 
         }
	
         current.next = prev;
         if (next != null){
        	 start.next = reverse(next, k);
         }
         return current;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedListNode start = new LinkedListNode(1);
		start.next = new LinkedListNode(2);
		start.next.next = new LinkedListNode(3);
		start.next.next.next = new LinkedListNode(4);
		start.next.next.next.next = new LinkedListNode(5);
		start.next.next.next.next.next = new LinkedListNode(6);
		start.next.next.next.next.next.next = new LinkedListNode(7);
		start.next.next.next.next.next.next.next = new LinkedListNode(8);
        LinkedListNode s = reverse(start, 3);
        System.out.println(s.data);
        
	}

}
