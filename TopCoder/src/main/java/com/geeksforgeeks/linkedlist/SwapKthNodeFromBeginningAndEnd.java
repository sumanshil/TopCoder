package com.geeksforgeeks.linkedlist;

public class SwapKthNodeFromBeginningAndEnd {

	
	private void swap(LinkedListNode start, int k, int n){
		LinkedListNode temp1 = start;
		LinkedListNode temp2 = start;
		
		LinkedListNode temp1_prev = null;
		LinkedListNode temp2_prev = null;
		
		for(int i = 1 ; i < k; i++){
			temp1_prev = temp1;
			temp1 = temp1.next;
		}
		
		for(int i = 1 ; i < (n-k+1); i++){
			temp2_prev = temp2;
			temp2 = temp2.next;
		}
		
		if (temp1_prev != null){
			temp1_prev.next = temp2;
		}
		
		if (temp2_prev != null){
			temp2_prev.next = temp1;
		}
		
		
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
		start.next.next.next.next.next.next.next = new LinkedListNode(7);

	}

}
